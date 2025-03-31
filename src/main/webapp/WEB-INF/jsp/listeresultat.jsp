<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listeresultat</title>
    <style>
        .filter-box { margin: 20px 0; padding: 10px; background: #f5f5f5; }
    </style>
</head>
<body>
    <h2>Listeresultat</h2>
    
    <!-- Enkel filtrering -->
    <div class="filter-box">
        <form action="searchWithFilters" method="get">
            <input type="hidden" name="query" value="${param.query}">
            
            Min. rating:
            <select name="minRating">
                <option value="">Alle</option>
                <option value="3" ${param.minRating == '3' ? 'selected' : ''}>3+ ⭐</option>
                <option value="4" ${param.minRating == '4' ? 'selected' : ''}>4+ ⭐</option>
            </select>
            
            Max. pris:
            <select name="maxPrice">
                <option value="">Alle</option>
                <option value="1" ${param.maxPrice == '1' ? 'selected' : ''}>1</option>
                <option value="2" ${param.maxPrice == '2' ? 'selected' : ''}>2</option>
                <option value="3" ${param.maxPrice == '3' ? 'selected' : ''}>3</option>
                <option value="4" ${param.maxPrice == '4' ? 'selected' : ''}>4</option>
            </select>
            
            <button type="submit">Filtrer</button>
            <a href="/places/searchText?query=${param.query}">Nullstill</a>
        </form>
    </div>

    <!-- Resultatliste -->
    <ul>
        <c:choose>
            <c:when test="${not empty places}">
                <c:forEach var="place" items="${places}">
                    <li>${place.displayName.text} - ${place.rating}⭐ - 
                        <c:choose>
                            <c:when test="${place.priceRange != null}">
                                ${place.priceRange.min} - ${place.priceRange.max}
                            </c:when>
                            <c:otherwise>
                                Ikke tilgjengelig
                            </c:otherwise>
                        </c:choose>
                        - ${place.address}
                    </li>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <li>Ingen resultater funnet</li>
            </c:otherwise>
        </c:choose>
    </ul>
</body>
</html>