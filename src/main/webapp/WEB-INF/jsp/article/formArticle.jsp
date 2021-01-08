<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Form Article</title>
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
    <form:form modelAttribute="article" method="post">
        <form:hidden path="id"/>

        <div class="d-flex">
            <div class="form-group form-check mx-5">
                <label class="form-check-label">
                    <form:checkbox path="draft" class="form-check-input" disabled="${disabledParam}"/> Szkic
                </label>
            </div>
            <div class="form-group mx-5">
                <label for="title">Tytuł:</label>
                <br><form:errors path="title" class="text-danger"/>
                <form:input id="title" path="title" class="form-control" disabled="${disabledParam}"/>
            </div>
            <div class="form-group mx-5">
                <label for="author">Autor:</label>
                <form:select id="author" path="author" class="form-control" disabled="${disabledParam}">
                    <form:option label="--Wybierz autora--" value="0"/>
                    <form:options items="${allAuthors}" itemLabel="fullName" itemValue="id"/>
                </form:select>
            </div>
            <div class="form-group mx-5">
                <label for="categories">Kategorie:</label>
                <br><form:errors path="categories" class="text-danger"/>
                <form:select id="categories" path="categories" items="${allCategories}" itemLabel="name" itemValue="id" multiple="true" class="form-control" disabled="${disabledParam}"/>
            </div>
        </div>

        <div class="form-group">
            <label for="content">Treść:</label>
            <br><form:errors path="content" class="text-danger"/>
            <form:textarea id="content" path="content" rows="5" cols="50" maxlength="1000" class="form-control" disabled="${disabledParam}"/>
        </div>
        <form:hidden path="created"/>
        <input type="submit" value="Zatwierdź" class="btn ${buttonClass} ml-3">
        <a href="/article/showAllArticles" class="btn btn-secondary ml-3">
            Anuluj i wróć do listy artykułów</a>
    </form:form>
</div>
</body>
</html>
