<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hjemmeside</title>
   
</head>
<body>
    
    <h2>Hjemmeside</h2>
    <form id="skjema" action="places/search" method="get">
            <input type="text" name="query" placeholder="Søk etter restauranter">
            <input type="submit" value="Search">
    </form>
    
    
</body>
</html>