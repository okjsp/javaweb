<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="article" class="photogallery.Article" scope="request" />
<!doctype html>
<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<div id="wrap">
    <%= article %>
    <h2>Add</h2>
    <form action="add.do" method="post" enctype="multipart/form-data">
        <input id="writer" name="writer" placeholder="writer..." required class="form">
        <br>
        <input id="title" name="title" placeholder="title..." required class="form">
        <br>
        <textarea id="content" name="content" required class="form"></textarea>
        <br>
        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>
