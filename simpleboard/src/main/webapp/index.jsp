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
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<div id="wrap">

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
        %>
        <tr>
            <td class="writer"><%= article.getWriter() %>
            </td>
            <td class="title"><a href="update.jsp?id=<%= article.getId() %>"><%= article.getTitle() %>
            </a></td>
            <td class="content"><%= article.getContent() %>
            </td>
            <td class="time">2017-09-24</td>
        </tr>
        <%
            }
            if (list.size() == 0) {
        %>
        <tr>
            <td colspan="4">empty
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <a href="add.jsp">Add</a>
</div>

</body>
</html>
