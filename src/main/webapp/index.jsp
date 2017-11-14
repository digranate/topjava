<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Java Enterprise (Topjava)</title>
</head>
<body>
<h3>Проект <a href="https://github.com/JavaWebinar/topjava" target="_blank">Java Enterprise (Topjava)</a></h3>

<form>
    <select name="user">
        <option value="1">1</option>
        <option value="2">2</option>
    </select>
    <input type="submit" value="Submit">
</form>
<hr>
<ul>
    <li><a href="users">Users</a></li>
    <li><a href="meals">Meals</a></li>
</ul>
</body>
</html>
