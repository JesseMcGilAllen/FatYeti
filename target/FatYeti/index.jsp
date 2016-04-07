<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

    <head>
     <title>Fat Yeti</title>
    </head>

    <body>

        <div class = "header">
            <H1>Fat Yet - ZIP Code Center Test</H1>
            <h2>Provide a ZIP code and I'll tell you latitude and longitude of the ZIP code center.</h2>
        </div>

        <div class = "form_settings">
            <form method="POST" action="/convertZIPCode">
                <p><span>ZIP Code</span><input type = "text" name = "zipCode" value = "" /></p>
                <p><span>&nbsp;</span><input type = "submit" value = "Enter"></p>
            </form>
        </div>

        <c:if test="${not empty latitude && not empty longitude}">
            <p>The coordinates of the center of the ZIP code are: ${latitude}, ${longitude}</p>
            <c:set var="latitude" value="" scope="session" />
            <c:set var="longitude" value="" scope="session" />
        </c:if>
        <p>${errorMessage}</p>
        <c:set var="errorMessage" value="" scope="session" />

    </body>

</html>

<!--
<html>
    <head>
        <title>FatYeti</title>
    </head>
    <body>
        <h3>Hi, I'm up and running!</h3>
    </body>
</html>
-->
