<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href='<c:url value="/css/style.css"/>' type="text/css"/>
	<script type="text/javascript" src="js/myscript.js"></script>
	<meta charset="UTF-8">
	<title>Title</title>
</head>
<body>
<div class = "container">
<h1><c:out value = "${product.name}"></c:out></h1>

  <div class="row">
    <div class="col-md-6">
      <h3>Categories:</h3>
      <ul>
      <c:forEach items="${product.categories}" var="category">
        <li>${category.name}</li>
</c:forEach>
</ul>
    </div>
    <div class="col-md-6">
      <form action = "/addCategory/${product.id}" method = "POST">
<div class="form-group">
    <label for="exampleFormControlSelect1">Add Category:</label>
    <select name = "cat" class="form-control" id="exampleFormControlSelect1">
     <c:forEach items="${nonCategories}" var="category">
        <option value = "${category.id}">${category.name}</option>
</c:forEach>
    </select>
  </div>
  <button type="submit" class="btn btn-primary">Add</button>
</form>
    </div>

  </div>
</div>

</body>
</html>