<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hjemmeside</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
   
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />
    
    <div class="container mt-5">
        <h2>Hjemmeside</h2>
        
        <c:if test="${not empty sessionScope.username}">
            <div class="mt-3 mb-4">
                <a href="/favorite/list" class="btn btn-primary">
                    <i class="bi bi-heart-fill"></i> Mine favoritter
                </a>
            </div>
        </c:if>
        
        <div class="row mt-2">
            <div class="col-md-6 mt-3">
                <form id="textForm" action="places/searchText" method="get" class="mb-3">
                    <div class="input-group">
                        <input type="text" name="query" placeholder="Søk etter restauranter" class="form-control">
                        <button class="btn btn-primary" type="submit">Søk</button>
                    </div>
                </form>
            </div>

            <div class="col-md-6">
                <label for="radius" class="form-label">Radius (m) for restauranter nær deg:</label>
                <input type="number" id="radius" placeholder="Meter" class="form-control mb-2">
                <div class="d-grid gap-2">
                    <button type="button" class="btn btn-secondary" onclick="getLocation('searchNearby')">
                        Vis restauranter nær deg
                    </button>
                    <button type="button" class="btn btn-secondary" onclick="getLocation('getRandom')">
                        Foreslå en tilfeldig restaurant
                    </button>
            </div>
        </div>

        <div id="currentLocation" class="mt-2 text-danger"></div>
    
    <script>
        
        function success(position, actionType){
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            
            // Determine the endpoint based on button clicked
            let actionUrl = "/places/";
            if (actionType === "searchNearby") {
                actionUrl += "searchNearby";
            } else if (actionType === "getRandom") {
                 actionUrl += "getRandom"; 
            }
     
            console.log(latitude);       
            console.log(longitude);
            console.log(actionUrl.href);
            
            if (latitude == null || longitude == null) {
                    console.error("Latitude or longitude is null/undefined.");
                    return;
                }
                
            // Creates a hidden form to handle submission of geolocation to controller
            const form = document.createElement("form");
            form.method = "GET";
            form.action = actionUrl; // Path to Spring Boot controller method
            
           
            
            const latInput = document.createElement("input");
            latInput.type = "hidden";
            latInput.name = "lat";
            latInput.value = latitude;
            form.appendChild(latInput);
            
            const lonInput = document.createElement("input");
            lonInput.type = "hidden";
            lonInput.name = "lon";
            lonInput.value = longitude;
            form.appendChild(lonInput);
            
            const radiusInput = document.createElement("input");
            radiusInput.type = "hidden";
            radiusInput.name = "radius"
            radiusInput.value = document.getElementById("radius").value;
            form.appendChild(radiusInput);
            
            document.body.appendChild(form);
            form.submit();
            
            
            }
            
        function error(){
            alert("Sorry, no position available");
            document.getElementById("currentLocation").innerText = "Beklager, kunne ikke hente posisjon.";
        }
        
        
        function getLocation(actionType) {
            if(!navigator.geolocation){
                document.getElementById("currentLocation").innerText = "Geolocation is not supported by your browser";
            } else {
                navigator.geolocation.getCurrentPosition(
                                (position) => success(position, actionType), 
                                error);
            }
           
        } 
        </script>
    
</body>
</html>