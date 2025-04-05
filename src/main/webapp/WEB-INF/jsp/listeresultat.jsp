<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listeresultat</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .price-available {
            color: #28a745;
            font-weight: bold;
        }
        .price-unavailable {
            color: #6c757d;
            font-style: italic;
        }
    </style>
</head>
<body class="bg-light">
    <jsp:include page="header.jsp" />

    <div class="container mt-5">
        <h2>Listeresultat</h2>
    
        <!-- Filter Section -->
        <div class="card mb-4">
            <div class="card-body">
                <form action="searchWithFilters" method="get" class="row g-3">
                    <input type="hidden" name="query" value="${query}">
                    <div class="col-md-4">
                        <label for="minRating" class="form-label">Min. rating:</label>
                        <select name="minRating" id="minRating" class="form-select">
                            <option value="">Alle</option>
                            <option value="3" ${param.minRating == '3' ? 'selected' : ''}>3+ ⭐</option>
                            <option value="4" ${param.minRating == '4' ? 'selected' : ''}>4+ ⭐</option>
                        </select>
                    </div>
                    <div class="col-md-4">
                        <label for="maxPrice" class="form-label">Max. pris:</label>
                        <select name="maxPrice" id="maxPrice" class="form-select">
                            <option value="">Alle</option>
                            <option value="100" ${param.maxPrice == '100' ? 'selected' : ''}>Under 100 NOK</option>
                            <option value="200" ${param.maxPrice == '200' ? 'selected' : ''}>Under 200 NOK</option>
                            <option value="300" ${param.maxPrice == '300' ? 'selected' : ''}>Under 300 NOK</option>
                            <option value="400" ${param.maxPrice == '400' ? 'selected' : ''}>Under 400 NOK</option>
                        </select>
                    </div>
                    <div class="col-md-4 d-flex align-items-end">
                        <button type="submit" class="btn btn-primary me-2">Filtrer</button>
                        <a href="/places/searchText?query=${query}" class="btn btn-secondary">Nullstill</a>
                    </div>
                </form>
            </div>
        </div>

        <!-- Results List -->
        <div class="list-group">
            <c:choose>
                <c:when test="${not empty places}">
                    <c:forEach var="place" items="${places}">
                        <a href="detail" class="list-group-item list-group-item-action">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">${place.displayName.text}</h5>
                                <small>${place.rating}⭐</small>
                            </div>
                            <p class="mb-1">${place.address}</p>
                            <small class="${place.priceRange != null ? 'price-available' : 'price-unavailable'}">
                                <c:choose>
                                    <c:when test="${place.priceRange != null}">
                                        ${place.priceRange.min} - ${place.priceRange.max} NOK
                                    </c:when>
                                    <c:otherwise>
                                        Pris ikke tilgjengelig
                                    </c:otherwise>
                                </c:choose>
                            </small>
                        </a>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info">Ingen resultater funnet</div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

                   
                
                
                
    <!-- 
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
    -->
    <!-- Resultatliste 
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
    -->
</body>
</html>