<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
    <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
        <td>
            ${meal.dateTime.toString().replace("T"," ")}
        </td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">Update</a></td>
        <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>

    </tr>
    </c:forEach>
</table>
<p><a href="meals?action=insert">Add Meal</a></p>

</body>
</html>