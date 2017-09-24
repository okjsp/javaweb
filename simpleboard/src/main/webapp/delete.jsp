<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="board" class="simpleboard.SimpleBoard" />
<%
    long id = new Integer(request.getParameter("id"));
    board.delete(id);
    response.sendRedirect("./index.jsp");
%>