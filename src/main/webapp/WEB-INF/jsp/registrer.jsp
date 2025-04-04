<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registrering</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        <h4 class="card-title mb-0">Registrering</h4>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty RAerrorMessages}">
                            <div class="alert alert-danger" role="alert">
                                <c:forEach var="errorMessage" items="${RAerrorMessages}">
                                    ${errorMessage}<br>
                                </c:forEach>
                            </div>
                        </c:if>
                        <form id="skjema" action="submitregistration" method="post">
                            <div class="mb-3">
                                <label for="phone" class="form-label">Phone number:</label>
                                <input type="number" class="form-control" name="phone" id="phone" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password:</label>
                                <input type="password" class="form-control" name="password" id="password" required>
                            </div>
                            <div class="mb-3">
                                <label for="passwordRepeated" class="form-label">Repeat password:</label>
                                <input type="password" class="form-control" name="passwordRepeated" id="passwordRepeated" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Register User</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>