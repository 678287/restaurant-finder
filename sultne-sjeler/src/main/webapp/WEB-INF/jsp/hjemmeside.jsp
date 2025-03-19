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
            <input type="submit" value="Search">
    </form>
    <button onclick="getLocation()">Show nearby restaurants</button>
    <script>
        
        function success(position){
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            
            const url = new URL("http://localhost:8080/places/searchNearby")
            url.searchParams.append("lat", latitude);
            url.searchParams.append("lon", longitude);
     
            console.log(latitude);       
            console.log(longitude);
            console.log(url.href);
            
            if (latitude == null || longitude == null) {
                    console.error("Latitude or longitude is null/undefined.");
                    return;
                }
                
            // Creates a hidden form to handle submission of geolocation to controller
            const form = document.createElement("form");
            form.method = "GET";
            form.action = "/places/searchNearby"; // Path to Spring Boot controller method
            
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
            
            document.body.appendChild(form);
            form.submit();
            
            
            }
            
        function error(){
            alert("Sorry, no position available");
        }
        
        
        function getLocation() {
            if(!navigator.geolocation){
                document.getElementById("currentLocation").innerText = "Geolocation is not supported by your browser";
            } else {
                navigator.geolocation.getCurrentPosition(success, error);
            }
           
        } 
        </script>
    
</body>
</html>