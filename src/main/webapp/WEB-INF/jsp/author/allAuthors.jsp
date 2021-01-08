<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Authors</title>
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
        <h2 class="float-left ml-3">Autorzy</h2>
        <a href="/author/addAuthor" class="float-right btn btn-success mr-3">Dodaj nowego autora</a>
    </div>

    <table class="table table-bordered table-hover table-sm">
        <thead class="bg-primary text-white">
        <tr>
            <th>Id</th>
            <th>Imię</th>
            <th>Nazwisko</th>
            <th>Edytuj</th>
            <th>Usuń</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allAuthors}" var="author">
            <tr>
                <td class="align-middle">${author.id}</td>
                <td class="align-middle">${author.firstName}</td>
                <td class="align-middle">${author.lastName}</td>
                <td class="text-center"><a href="/author/editAuthor/${author.id}" class="btn btn-warning btn-sm">Edytuj</a></td>
                <td class="text-center"><a href="/author/deleteAuthor/${author.id}" class="btn btn-danger btn-sm">Usuń</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
