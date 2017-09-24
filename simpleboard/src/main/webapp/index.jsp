<%@ page import="java.util.List" %>
<%@ page import="simpleboard.Article" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="board" class="simpleboard.SimpleBoard" scope="application"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Simple Board List</title>
</head>
<body>
<h2>Simple Board</h2>
${param.test }

<table>
    <tr>
        <th>Writer</th>
        <th>Title</th>
        <th>Content</th>
        <th>Time</th>
    </tr>
    <%
        List<Article> list = board.getList();
        for (Article article : list) {
            System.out.println(article.getWriter());
    %>
    <tr>
        <td><%= article.getWriter() %></td>
        <td><a href="update.jsp?id=<%= article.getId() %>"><%= article.getTitle() %></a></td>
        <td><%= article.getContent() %></td>
        <td>2017-09-24</td>
    </tr>
    <%
        }
    %>
</table>
<a href="add.jsp">Add</a>
</body>
</html>
