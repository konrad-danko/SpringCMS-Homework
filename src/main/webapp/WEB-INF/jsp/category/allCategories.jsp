<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Categories</title>
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
    <div class="clearfix">
        <h2 class="float-left ml-3">Kategorie</h2>
        <a href="/category/addCategory" class="float-right btn btn-success mr-3">Dodaj nową kategorię</a>
    </div>

    <table class="table table-bordered table-hover table-sm">
        <thead class="bg-primary text-white">
        <tr>
            <th>Id</th>
            <th>Nazwa</th>
            <th>Opis</th>
            <th>Edytuj</th>
            <th>Usuń</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allCategories}" var="category">
            <tr>
                <td class="align-middle">${category.id}</td>
                <td class="align-middle">${category.name}</td>
                <td class="align-middle">${category.description}</td>
                <td class="text-center"><a href="/category/editCategory/${category.id}" class="btn btn-warning btn-sm">Edytuj</a></td>
                <td class="text-center"><a href="/category/deleteCategory/${category.id}" class="btn btn-danger btn-sm">Usuń</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
