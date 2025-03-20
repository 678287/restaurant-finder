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
    
   
    <form id="textForm" action="places/searchText" method="get">
            <input type="text" name="query" placeholder="Søk etter restauranter">
            <input type="submit" value="Søk">
    </form><br>
    <label for="radius">Radius (m) for restauranter nær deg:</label><br>
    <input type="number" id="radius" placeholder="Meter">
    <button onclick="getLocation('searchNearby')">Vis restauranter nær deg</button><br><br>
    <button onclick="getLocation('getRandom')">Foreslå en tilfeldig restaurant</button>
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