<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
   
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="card-title mb-0">Login</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty redirectMessage}">
                            <div class="alert alert-danger" role="alert">
                                ${redirectMessage}
                            </div>
                        </c:if>
                        <form id="skjema" action="tryLogin" method="post">
                            <div class="mb-3">
                                <label for="phone" class="form-label">Phone number:</label>
                                <input class="form-control" name="phone" id="phone" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password:</label>
                                <input type="password" class="form-control" name="password" id="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary" value="Log in">Log In</button>
                        </form>   
    <!--
    <h2>Login</h2>
    <p style="color:red;">${redirectMessage}</p>
                
        
        <fieldset>
            <form id="skjema" action="tryLogin" method="post">
                <label for="phone">Phone number:</label>
                <input type="number" name="phone" id="phone"><br>
                <label for="password">Password:</label>
                <input type="password" name="password" id="password"><br>
                <input type="submit" value="Log in">
            </form>   
        </fieldset>
    -->
    
</body>
</html>