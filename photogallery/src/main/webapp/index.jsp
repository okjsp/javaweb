<%@ page import="java.util.List" %>
<%@ page import="photogallery.Article" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="board" class="photogallery.PhotoGallery" scope="application"/>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Photo Gallery List</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<div id="wrap">

    <h2>Photo Gallery</h2>
    ${param.test }

    <table>
        <tr>
            <th>Writer</th>
            <th>Title</th>
            <th>Content</th>
        </tr>
        <%
            List<Article> list = board.getList();
            for (Article article : list) {
        %>
        <tr>
            <td class="writer"><%= article.getWriter() %>
            </td>
            <td class="title"><a href="update.do?id=<%= article.getId() %>"><%= article.getTitle() %>
            </a></td>
            <td class="content"><%= article.getContent() %>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <%= article.getContent() %>
                <br>
                <img src="/image.do?<%= article.getSaveName() %>" class="thumb"/>
            </td>
        </tr>
        <%
            }
            if (list.size() == 0) {
        %>
        <tr>
            <td colspan="3">empty
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
