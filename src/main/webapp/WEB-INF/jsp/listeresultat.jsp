<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listeresultat</title>
   
</head>
<body>
    
    <h2>Listeresultat</h2>
 
    <ul>
        
        <c:forEach var="place" items="${places}">
            <li>${place.displayName.text} - ${place.rating}⭐ - ${place.address}</li>
        </c:forEach>

    </ul>
    
    
</body>
</html>