<%@page import="entity.Paniers"%>
<%@page import="entity.Products"%>
<%@page import="bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Panier</title>
</head>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	text-align: center;
}

h1 {
	font-size: 24px;
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin: 20px 0;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 10px;
	text-align: center;
	border: 1px solid #ddd;
}

th {
	background-color: #007bff;
	color: #fff;
	font-weight: bold;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
	transition: background-color 0.3s;
}

form {
	margin-top: 20px;
}

input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

input[type="hidden"] {
	display: none;
}

/* Hover effect for Remove buttons */
form:hover input[type="submit"] {
	background-color: #0056b3;
	transition: background-color 0.3s;
}
</style>
<body>
	<%
	double prixTotal = (double) request.getAttribute("prixTotal");
	%>
	<%
	ArrayList<Products> panier = (ArrayList<Products>) request.getAttribute("panier");
	ArrayList<entity.Paniers> panier2 = (ArrayList<Paniers>) request.getAttribute("panier2");
	%>

	<h1>Mon Panier :</h1>
	<%
	if (panier.isEmpty()) {
	%>
	<p>Votre panier est vide.</p>
	<%
	} else {
	%>

	<table border="1">
		<tr>
			<th>Product Name</th>
			<th>Price</th>
			<th>Stock</th>
			<th>Quantité</th>

			<th>Action</th>
		</tr>



		<c:forEach items="${panier}" var="product" varStatus="loopStatus">
			<tr>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>${product.stock_quantity}</td>
				<td>${panier2[loopStatus.index].getQuantity()}</td>
				<td>
					<form action="removeProduct" method="post">
						<input type="hidden" name="productId"
							value="${product.product_id}" /> <input
							type="hidden" name="userId" value="${idUsername}" />
						<input type="hidden" name="panierId" value="${idUsername}" />

						<input type="submit" value="Supprimer" />
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	<form action="ViderPanier" method="post">
		<input type="hidden" name="userId" value="${idUsername}" /> <input
			type="submit" value="Vider le Panier" />
	</form>

	<form action="Paiement" method="post">
		<input type="hidden" name="idUser" value="${idUsername}" /> <input
			type="hidden" name="prixTotal" value="${prixTotal}" /> <input
			type="submit" value="Paiement" />
	</form>
	<%
	}
	%>
</body>
</html>
