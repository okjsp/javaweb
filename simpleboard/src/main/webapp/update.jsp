<%@ page import="simpleboard.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="board" class="simpleboard.SimpleBoard" scope="application"/>
<%
    long id = new Long(request.getParameter("id"));
    Article article = board.get(id);
%>
<!doctype html>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2>Add</h2>
<form action="add2.jsp" method="post">
    <input type="hidden" name="id" value="<%= article.getId() %>">
    <input id="writer" name="writer" placeholder="writer..." value="<%= article.getWriter() %>" required>
    <br>
    <input id="title" name="title" placeholder="title..." value="<%= article.getTitle() %>" required>
    <br>
    <textarea id="content" name="content" value="<%= article.getContent() %>" required><%= article.getContent() %></textarea>
    <br>
    <input type="submit" value="Update">
</form>
</body>
</html>
