<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="board" class="simpleboard.SimpleBoard" />
<jsp:useBean id="article" class="simpleboard.Article" >
    <jsp:setProperty name="article" property="id" />
    <jsp:setProperty name="article" property="title" />
    <jsp:setProperty name="article" property="content" />
</jsp:useBean>
<%
    board.add(article);
    response.sendRedirect("./index.jsp");
%>