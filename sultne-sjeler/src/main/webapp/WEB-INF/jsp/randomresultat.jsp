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
    
    <h2>Random result</h2>

    <h3>${place.displayName.text}</h3>
    <p>${place.rating}⭐ - ${place.address}</p>
    
    
</body>
</html>