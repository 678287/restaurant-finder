<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your Favorite Restaurants</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />
    
    <div class="container mt-5">
        <h2>Your Favorite Restaurants</h2>
        <c:choose>
            <c:when test="${not empty favorites}">
                <ul class="list-group">
                    <c:forEach var="place" items="${favorites}">
                        <li class="list-group-item">
                            <h5>${place.displayName.text}</h5>
                            <p>Address: ${place.address}</p>
                            <p>Rating: ${place.rating}⭐</p>
                            
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <div class="alert alert-info">
                    You haven't added any favorite restaurants yet.
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    
</body>
</html>
