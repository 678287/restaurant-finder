<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kartresultat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #map-img {
            width: 100%;
            max-width: 600px;
            height: auto;
        }
    </style>
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />
    
    <div class="container mt-5">
        <h2>Kartresultat</h2>
        <div class="card">
            <img id="map-img" src="${staticMapUrl}" class="card-img-top" alt="Map of restaurants">
            <div class="card-body">
                <p class="card-text">This map displays the locations of the restaurants.</p>
            </div>
        </div>
    </div>
</body>
</html>