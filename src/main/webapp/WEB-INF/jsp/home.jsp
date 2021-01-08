<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
    <%@ include file="header.jsp" %>

    <nav class="navbar navbar-expand-sm">
        <ul class="navbar-nav">
            <c:forEach items="${allCategoriesSorted}" var="category">
                <li class="nav-item">
                    <a href="/home/categoryArticles/${category.id}" class="btn btn-outline-info ml-3 shadow">${category.name}</a>
                </li>
            </c:forEach>
        </ul>
    </nav>
    <br>

    <c:if test="${not empty allArticlesWithGivenCategory}">
        <h3>Artykuły z kategorii ${categoryName}</h3>
        <table class="table table-bordered table-hover table-sm">
            <thead class="bg-primary text-white">
            <tr>
                <th>Id</th>
                <th>Tytuł</th>
                <th>Autor</th>
                <th>Kategorie</th>
                <th>Treść</th>
                <th>Utworzono</th>
                <th>Zaktualizowano</th>
                <th>Szkic</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allArticlesWithGivenCategory}" var="article">
                <tr>
                    <td>${article.id}</td>
                    <td>${article.title}</td>
                    <td>${article.author.fullName}</td>
                    <td>
                        <c:forEach items="${article.categories}" var="category">${category.name}<br></c:forEach>
                    </td>
                    <td>${article.content}</td>
                    <td>${article.created.toLocalDate()} ${article.created.toLocalTime()}</td>
                    <td>${article.updated.toLocalDate()} ${article.updated.toLocalTime()}</td>
                    <td class="text-center">${article.draft==true ? "T" : "N"}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
    </c:if>

    <h3>Ostatnio dodane</h3>
    <table class="table table-bordered table-hover table-sm">
        <thead class="bg-primary text-white">
        <tr>
            <th>Id</th>
            <th>Tytuł</th>
            <th>Utworzono</th>
            <th>Treść</th>
            <th>Autor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${recent5Articles}" var="article">
            <tr>
                <td>${article.id}</td>
                <td>${article.title}</td>
                <td>${article.created.toLocalDate()} ${article.created.toLocalTime()}</td>
                <td>${article.content}</td>
                <td>${article.author.fullName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>
