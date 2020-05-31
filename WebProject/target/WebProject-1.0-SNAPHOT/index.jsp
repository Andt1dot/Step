<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Main</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="css/bootstrap.css" rel="stylesheet"/>
 <link  href="css/style.css" type="text/css"  rel="stylesheet"/>
</head>
<body class="other">
<header class="container bg-dark p-1 text-white rounded">
<div class="row">
<div class="col-8">
<h3>HomePage</h3>	
</div>
<div class="col-2">
<label class="mt-1"for="#">UserName</label>
</div>
<div class="col-2">
<a class="btn btn-danger p-1" href="Log In.html" role="button">Logout</a>
</div>
</div>
</header>

<!---Search -->
<div class="container"> 
<div class="row mt-3">
  <div class="col-6">
  <div class="btn-group">
  <a class="btn btn-dark disabled" href="#" role="button">HomePage</a>
  <a class="btn btn-dark" href="Add_Student.html" role="button">Add/Edit list of students</a>
  <a class="btn btn-dark" href="Add_Interes.html" role="button">Add/Edit list of interes</a>
  </div>
  </div>
  <div class="col-6">
  </div>  
</div>
<form action="index.html" method="get">
	<div class="row mt-5">
	<div class="col-1">
	</div>
	<div class="col-4">
	<input class="form-control" type="text" maxlength="10" placeholder="Name or LastName" id="search">
	</div>
	<div class="col-4">
	<input class="form-control text-center" type="number" maxlength="3" placeholder="Age" id="search">
	</div>
	<div class="col-1">
	<button class="btn btn-outline-secondary border-white text-white">Search</button>
  </div>
   </div>
   </form>
</div>
 <!---TABLE-->
 <div class="container mt-5">
 	<div class="row">
  <table class="table table-bordered rounded">
    <thead class="text-center text-white" >
      <tr>
        <th>Nr.</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Age</th>
        <th>Genre</th>
        <th>IdentityCode</th>
        <th>Birthdate</th>
      </tr>
    </thead>
    <tbody class="text-center text-white">
      <c:forEach items="${emp}" var="employee">
          <tr>
             <td><c:out value="${employee.idEmployeeDb}"/></td>
             <td><c:out value="${emp1oyee.name}"/></td>
             <td><c:out value="${emp1oyee.surname}"/></td>
             <td><c:out value="${emp1oyee.age}"/></td>
             <td><c:out value="${emp1oyee.genre}"/></td>
             <td><c:out value="${emp1oyee.identityCode}"/></td>
             <td><c:out value="${emp1oyee.birthdate}"/></td>
         </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</div>

<!--PAGINATION-->
<div class="container">
<div class="row justify-content-center">
  <ul class="pagination">
    <li class="page-item disabled">
      <a class="page-link" href="#" tabindex="-1" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
        <span class="sr-only">Previous</span>
      </a>
    </li>
    <li class="page-item active">
      <a class="page-link" href="#">1 <span class="sr-only">(current)</span></a>
    </li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <li class="page-item"><a class="page-link" href="#">4</a></li>
    <li class="page-item"><a class="page-link" href="#">5</a></li>
    <li class="page-item"><a class="page-link" href="#">6</a></li>
    <li class="page-item"><a class="page-link" href="#">7</a></li>
    <li class="page-item"><a class="page-link" href="#">8</a></li>
    <li class="page-item"><a class="page-link" href="#">9</a></li>
    <li class="page-item"><a class="page-link" href="#">10</a></li>
    <li class="page-item">
      <a class="page-link" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        <span class="sr-only">Next</span>
      </a>
    </li>
  </ul>
</div>
</div>
<footer id="sticky-footer" class="container p-2 bg-dark text-white-50 rounded fixed-bottom">
    <div class="container text-center text-white my">
      Copyright &copy; Cebotari Marin
    </div>
  </footer>
</body>
</html>
