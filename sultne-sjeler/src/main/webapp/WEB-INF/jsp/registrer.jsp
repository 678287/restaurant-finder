<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrering</title>
   
</head>
<body>
    
    <h2>Registrering</h2>
    <p style="color:red;">
        <c:forEach var="errorMessage" items="${RAerrorMessages}">
            ${errorMessage}<br>
         </c:forEach>
     </p>
     
     <fieldset>
        <form id="skjema" action="submitregistration" method="post">
            <label for="phone">Phone number:</label>
            <input type="number" name="phone" id="phone"><br>
            <label for="password">Password:</label>
            <input type="password" name="password" id="password"><br>
            <label for="passwordRepeated">Repeat password:</label>
            <input type="password" name="passwordRepeated" id="passwordRepeated"></br>
            <input type="submit" value="Register user">
        </form>
    </fieldset>
    
</body>
</html>