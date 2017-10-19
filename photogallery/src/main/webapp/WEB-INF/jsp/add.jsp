<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="article" class="photogallery.Article" scope="request" />
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!doctype html>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div id="wrap">
    <h2>${action}</h2>
    <form action="${fn:toLowerCase(action)}.action" method="post" enctype="multipart/form-data">
        <input id="writer" name="writer" placeholder="writer..."
               value="${article.writer}" required class="form">
        <br>
        <input id="title" name="title" placeholder="title..."
               value="${article.title}" required class="form">
        <br>
        <textarea id="content" name="content"
                  required class="form">${article.content}</textarea>
        <br>
        <c:choose>
            <c:when test="${article.filename ne null}">
                <img src="/image.action?${article.saveName}" class="thumb"/>
                <input id="fileName" name="filename" type="hidden" value="${article.filename}">
                <input id="fileSize" name="fileSize" type="hidden" value="${article.fileSize}">
                <input id="saveName" name="saveName" type="hidden" value="${article.saveName}">
                <input id="file" name="file" type="file" placeholder="image file" >
            </c:when>
            <c:otherwise>
                <input id="file" name="file" type="file" placeholder="image file" required>
            </c:otherwise>
        </c:choose>

        <br>
        <input id="id" name="id" type="hidden" value="${article.id}">
        <input type="submit" value="${action}">
    </form>
</div>
</body>
</html>
