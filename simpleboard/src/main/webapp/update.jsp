<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title>Add</title>
</head>
<body>
<h2>Add</h2>
<form action="add2.jsp" method="post">
    <input id="writer" name="writer" placeholder="writer..." required>
    <br>
    <input id="title" name="title" placeholder="title..." required>
    <br>
    <textarea id="content" name="content" required></textarea>
    <input type="submit" value="Update">
</form>
</body>
</html>
