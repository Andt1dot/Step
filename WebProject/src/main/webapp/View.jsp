
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View</title>
    </head>
    <body>
        <h1>List of employees</h1>
        <ul>
            <c:forEach items="${ListOfEmployee}" var="e">
                <li><c:out value="${e}"/></li>
           </c:forEach>
        </ul>
       
    </body>
</html>
