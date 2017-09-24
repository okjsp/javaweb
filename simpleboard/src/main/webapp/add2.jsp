<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="board" class="simpleboard.SimpleBoard" scope="application"/>
<jsp:useBean id="article" class="simpleboard.Article" >
    <jsp:setProperty name="article" property="id" />
    <jsp:setProperty name="article" property="writer" />
    <jsp:setProperty name="article" property="title" />
    <jsp:setProperty name="article" property="content" />
</jsp:useBean>
<%= article.getWriter() %>
<%
    if (article.getId() == 0) {
        article.setId(board.size() + 1);
        board.add(article);
    } else {
        board.update(article);
    }
    response.sendRedirect("./index.jsp");
%>