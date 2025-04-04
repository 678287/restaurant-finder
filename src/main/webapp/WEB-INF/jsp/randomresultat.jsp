<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listeresultat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
   
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <h2>Random result</h2>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${place.displayName.text}</h5>
                <h6 class="card-subtitle mb-2 text-muted">${place.rating}⭐</h6>
                <p class="card-text">${place.address}</p>
            </div>
        </div>
    </div>
</body>
</html>