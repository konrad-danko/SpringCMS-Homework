<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>App Header</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a href="/home" class="btn btn-info ml-3">Strona główna</a>
        </li>
        <li class="nav-item">
            <a href="/category/showAllCategories" class="btn btn-info ml-4">Kategorie</a>
        </li>
        <li class="nav-item">
            <a href="/author/showAllAuthors" class="btn btn-info ml-4">Autorzy</a>
        </li>
        <li class="nav-item">
            <a href="/article/showAllArticles" class="btn btn-info mx-4">Artykuły</a>
        </li>
    </ul>
    <h1 class="navbar-text ml-5">Zarządzanie gazetą</h1>
</nav>
<br>

</body>
</html>
