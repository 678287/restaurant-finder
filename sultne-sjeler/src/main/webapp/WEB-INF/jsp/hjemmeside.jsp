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
            function getLocation() {
                if (navigator.geolocation) {
                    navigator.geolocation.getCurrentPosition(
                        (position) => {
                            const latitude = position.coords.latitude;
                            const longitude = position.coords.longitude;

                            fetch(`http://localhost:8080/places/searchNearby?lat=${latitude}&lon=${longitude}`)
                                .then(response => response.json())
                                .then(data => {
                                    document.getElementById("currentLocation").innerText = JSON.stringify(data, null, 2);
                                })
                                .catch(error => console.error("Error:", error));
                        },
                        (error) => {
                            console.error("Error getting location:", error);
                        }
                    );
                } else {
                    console.log("Geolocation is not supported by this browser.");
                }
            }
        </script>
    
</body>
</html>