<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Photo Gallery List</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div id="wrap">

    <h1>Photo Gallery!</h1>

    <table>
        <tr>
            <th>Writer</th>
            <th>Title</th>
            <th>Content</th>
            <th>x</th>
        </tr>
        <c:forEach items="${list}" var="article">
            <tr>
                <td class="writer">${article.writer}
                </td>
                <td class="title"><a href="add.action?id=${article.id}">${article.title}
                </a></td>
                <td class="content">${article.content}
                </td>
                <td class="delete"><a href="javascript:deleteFile(${article.id})">x</a>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    ${article.content}
                    <br>
                    <img src="/image.action?${article.saveName}" class="thumb"/>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty list}">
        <tr>
            <td colspan="4">empty
            </td>
        </tr>
        </c:if>
    </table>
    <a href="add.action">Add</a>
</div>
<form id="idform" style="display: none;">
    <input name="id">
</form>
<script>
    function deleteFile(id) {
        var form = document.getElementById("idform");
        form.action = "/delete.action";
        form.method = "post";
        document.getElementsByName("id")[0].value = id;
        form.submit();
    }
</script>
</body>
</html>
