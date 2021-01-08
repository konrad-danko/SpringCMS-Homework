<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Form Category</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@include file="../header.jsp"%>
    <br>
    <h2>${headerMessage}</h2>
    <br>
    <form:form modelAttribute="category" method="post">
        <form:hidden path="id"/>
        <div class="form-group">
            <label for="name">Nazwa:</label>
            <br><form:errors path="name" class="text-danger"/>
            <form:input path="name" class="form-control" id="name" disabled="${disabledParam}"/>
        </div>
        <div class="form-group">
            <label for="description">Opis kategorii:</label>
            <form:input path="description" class="form-control" id="description" disabled="${disabledParam}"/>
        </div>
        <input type="submit" value="Zatwierdź" class="btn ${buttonClass} ml-3">
        <a href="/category/showAllCategories" class="btn btn-secondary ml-3">
            Anuluj i wróć do listy kategorii</a>
    </form:form>
</div>
</body>
</html>
