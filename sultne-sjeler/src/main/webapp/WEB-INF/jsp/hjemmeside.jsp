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
    <button onclick="getLocation()">Get My Location</button>
    <p id="currentLocation"></p>
    <form id="locationForm" action="places/searchNearby" method="get">  
        <input type="submit" value="Show restaurants">
    </form>
    <form id="textForm" action="places/searchText" method="get">
            <input type="text" name="query" placeholder="Søk etter restauranter">
            <input type="submit" value="Search">
    </form>
    <script>
        
        function success(position){
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            const url = new URL("http://localhost:8080/places/searchNearby")
            url.searchParams.append("lat", latitude);
            url.searchParams.append("lon", longitude);
             //const url = `http://localhost:8080/places/searchNearby?lat=${latitude}&lon=${longitude}`
            
            console.log(longitude);
            console.log(latitude);
            console.log(url);
            console.log(url.href);
            
            if (latitude == null || longitude == null) {
                    console.error("Latitude or longitude is null/undefined.");
                    return;
                }
            
            fetch(url)
                .then((response) => response.json())
                .then((data) => {
                    document.getElementById("currentLocation").innerText = JSON.stringify(data, null, 2);
                })
                .catch((error) => console.error("Error:", error)); 
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