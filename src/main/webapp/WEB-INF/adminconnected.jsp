<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion admin</title>
</head>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #2c3e50;
    color: #ecf0f1;
    margin: 0;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

h2 {
    color: #3498db;
}

ul {
    list-style-type: none;
    padding: 0;
}

li {
    margin-bottom: 10px;
}

form {
    margin-bottom: 20px;
    width: 50%;
}

label {
    display: block;
    margin-bottom: 5px;
    color: #ecf0f1;
}

input[type="text"],
input[type="number"],
textarea {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    box-sizing: border-box;
}

input[type="submit"] {
    background-color: #3498db;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #2980b9;
}

table {
    border-collapse: collapse;
    width: 100%;
}

table, th, td {
    border: 1px solid #3498db;
}

th, td {
    padding: 10px;
    text-align: left;
}

th {
    background-color: #2980b9;
    color: white;
}

.cif-container {
    margin-top: 20px;
    width: 80%;
}

.cif-container table,
.cif-container th,
.cif-container td {
    border: 1px solid #3498db;
}

.cif-container th,
.cif-container td {
    padding: 10px;
    text-align: left;
}

.cif-container th {
    background-color: #2980b9;
    color: white;
}

.cif-container input[type="submit"] {
    background-color: #e74c3c;
}

.cif-container input[type="submit"]:hover {
    background-color: #c0392b;
}

</style>
<body>
Bonjour vous êtes connecté en tant qu'administrateur<br>

<h2>Liste des Clients</h2>
    <ul>
        <c:forEach items="${clients}" var="username">
           <form action="InfoClient" method="post">
    <input type="hidden" name="username" value=${username}>

    <input type="submit" value=${username}>
  </form>
        </c:forEach>
    </ul>

<h2>Liste des modérateurs</h2>
    <ul>
        <c:forEach items="${modo}" var="username">
    <form action="InfoClient" method="post">
    <input type="hidden" name="username" value=${username}>

    <input type="submit" value=${username}>
  </form>
        </c:forEach>
    </ul>


 <h2>Ajouter un produit</h2>
    <form action=Product method="POST">

        <label for="name">Nom du produit:</label>
        <input type="text" name="name" id="name" required><br>

        <label for="description">Description du produit:</label>
        <textarea name="description" id="description"></textarea><br>

        <label for="price">Prix du produit:</label>
        <input type="number" name="price" id="price" step="0.01" required><br>

        <label for="stock_quantity">Quantité en stock:</label>
        <input type="number" name="stock_quantity" id="stock_quantity" required><br>
        
        <label for="category_id">Catégorie :</label>
        <input type="text" name="category_id" id="category_id" required><br>
        
		<label for="category_id">Lien de l'image :</label>
        <input type="text" name="image" id="image" required><br>
        <input type="submit" value="Ajouter le produit">
        
       
        
    </form>


 <form action=RechercheProductAdmin method="POST">
 
		<h2>Supprimer un produit : </h2>
        <label for="recherche">Rechercher un produit :</label>
        <input type="text" id="recherche" name="recherche">
        <input type="submit" value="Rechercher">
 
 </form>


 <h2>Résultats de la recherche :</h2>
    <table border="1">
        <tr>
            <th>ID du produit</th>
            
            <th>Nom du produit</th>
            <th>Description</th>
            <th>Stock restant</th>
            
            <th>Action</th>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                 <td>${product.product_id}</td>
            
                <td>${product.name}</td>
                <td>${product.description}</td>
                <td>${product.stock_quantity}</td>
                <td>
                    <form action="SupressProduct" method="POST">
                        <input type="hidden" name="idproduct" value="${product.product_id}" />
                        <input type="submit" name="deleteFromStock" value="Supprimer un élément du stock" />
                        <input type="submit" name="deleteFromDB" value="Supprimer de la base de données" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>



</body>
</html>