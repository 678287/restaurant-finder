<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Kartresultat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        #map {
            width: 100%;
            height: 400px; 
        }
    </style>
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />
    
    <div class="container mt-5">
        <h2>Kartresultat</h2>
        <div class="card">
            <div id="map"></div>
            <div class="card-body">
                <p class="card-text">Klikk på markørene for mer informasjon.</p>
            </div>
        </div>
    </div>

    
    <script>
        const places = [
        <c:forEach var="place" items ="${places}" varStatus="status">
            {
                name: "${fn:escapeXml(place.displayName.text)}",
                lat: ${place.location.latitude},
                lng: ${place.location.longitude},
                address: "${fn:escapeXml(place.address)}",
                rating: ${place.rating},
                priceRange: "${place.priceRange eq null ? 0 : place.priceRange}"      
                }
                <c:if test="${!status.last}">,</c:if>
        </c:forEach>
        ];
        
        function initMap(){
            const defaultCenter = places.length > 0 ? { lat : places[0].lat, lng: places[0].lng } : { lat : 59.409, lng: 5.27 };
            
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom : 13,
                center: defaultCenter,
            });
            
            places.forEach(place => {
                
                const marker = new google.maps.Marker({
                    position: {lat: place.lat, lng: place.lng},
                    map: map,
                    title: place.name,
                    rating: place.rating,
                    priceRange: place.priceRange
                });
                
                
                let contentString = 
                '<div id="content">' +
                    '<div id="siteNotice">' +
                    "</div>" +
                    '<h1 id="firstHeading" class="firstHeading">'+place.name+'</h1>' +
                    '<div id="bodyContent">' +
                    "<p><b>Addresse: </b>" + place.address + '</p></b>' +
                    "<p><b>Rating: </b>" + place.rating + '⭐</p></b>' +
                    "<p><b>Prisklasse: </b>" + place.priceRange + '</p></b>'
                    "</div>" +
                    "</div>";


                const infoWindow = new google.maps.InfoWindow({
                    content: contentString
                });
                
                marker.addListener("click", () => {
                           infoWindow.open(map, marker);
                });
            });
         }
         
  </script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAMrl0FSl-JZUbQKxM83dy6eLj4dqHlA4A&callback=initMap" async defer></script>     
</body>
</html>