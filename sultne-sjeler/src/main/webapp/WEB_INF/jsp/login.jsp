<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
   
</head>
<body>
    
    <h2>Login</h2>
    <p style="color:red;">${redirectMessage}</p>
                
        
        
            <form id="skjema" action="tryLogin" method="post">
                <label for="phone">Phone number:</label>
                <input type="number" name="phone" id="phone"><br>
                <label for="password">Password:</label>
                <input type="password" name="password" id="password"><br>
                <input type="submit" value="Log in">
            </form>   
    
    
</body>
</html>