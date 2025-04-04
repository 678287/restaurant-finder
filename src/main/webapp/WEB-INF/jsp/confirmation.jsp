<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
   
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <div class="alert alert-success" role="alert">
            <h4 class="alert-heading">Confirmation</h4>
            <p>Your registration or login was successful.</p>
            <hr>
            <p class="mb-0">You can now explore the site.</p>
        </div>
    </div>
    <!--legg til hurtiglink hjemmeside. evnt logo venstrehjørnet oppe-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>