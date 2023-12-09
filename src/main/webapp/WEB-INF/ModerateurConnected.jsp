<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion mod&eacute;rateur</title>
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

    button {
        text-align: right;
        max-width: 100%;
        margin: 0;
    }

    button {
        align-self: flex-end;
        background-color: #ff9900; /* Couleur de fond du bouton */
        color: #333; /* Couleur du texte du bouton */
        padding: 10px 20px; /* Espacement intérieur du bouton */
        font-size: 16px; /* Taille de la police du texte */
        border: none; /* Supprime la bordure du bouton */
        border-radius: 5px; /* Ajoute une bordure arrondie au bouton */
        cursor: pointer; /* Change le curseur au survol du bouton */
        transition: background-color 0.3s; /* Ajoute une transition fluide à la couleur de fond du bouton */
    }

    button:hover {
        background-color: #ffcc66; /* Couleur de fond du bouton au survol */
    }

</style>
<body>
<form  name='deconnection' action="Accueil" method="get">
    <button type="submit">D&eacute;connexion</button>
</form>

Bonjour vous &ecirc;tes connect&eacute; en tant que mod&eacute;rateur ${sessionScope.username}<br>


 <h2>Ajouter un produit</h2>
    <form action=ProductModerateur method="POST">

        <label for="name">Nom du produit:</label>
        <input type="text" name="name" id="name" required><br>

        <label for="description">Description du produit:</label>
        <textarea name="description" id="description"></textarea><br>

        <label for="price">Prix du produit:</label>
        <input type="number" name="price" id="price" step="0.01" required><br>

        <label for="stock_quantity">Quantit&eacute; en stock:</label>
        <input type="number" name="stock_quantity" id="stock_quantity" required><br>
        
        <label for="category_id">Cat&eacute;gorie :</label>
        <input type="text" name="category_id" id="category_id" required><br>
        
        <label for="category_id">Lien de l'image :</label>
        <input type="text" name="image" id="image" required><br>
        <input type="submit" value="Ajouter le produit">
        
       <input type="hidden" name="username" value=${sessionScope.username}>
       
        
    </form>


 <form action=ProductModerateur method="GET">
 
		<h2>Supprimer un produit : </h2>
        <label for="recherche">Rechercher un produit :</label>
        <input type="text" id="recherche" name="recherche">
        <input type="hidden" name="username" value=${sessionScope.username}>
        
        <input type="submit" value="Rechercher">
        
 
 </form>

 <h2>R&eacute;sultats de la recherche :</h2>
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
                    <form action="SupressProductModerateur" method="POST">
                        <input type="hidden" name="idproduct" value="${product.product_id}" />
                        <input type="hidden" name="username" value="${sessionScope.username}" />
                        
                        <input type="submit" name="deleteFromStock" value="Supprimer un &eacute;l&eacute;ment du stock" />
                        <input type="submit" name="deleteFromDB" value="Supprimer de la base de donn&eacute;es" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

<table>
    <tr>
        <th>Products</th>
    </tr>
    <tr>
        <td>
            <div style="text-align: center;">
                <c:forEach items="${products}" var="product" varStatus="loop">
                    <div style="display: inline-block; margin: 10px; text-align: center; width: 30%;">

             <a href="InfoProductModo?productId=${product.product_id}&username=${sessionScope.username}"> <!-- Redirige vers la servlet avec un paramètre productId -->
                            <img src="${product.image}" alt="" width="200" height="200">
                            <p>${product.name}</p>
                            <p>${product.price} &euro;</p>
                        </a>
                    </div>

                    <c:if test="${loop.index % 3 == 2}">
                        <br> <!-- Ajouter un saut de ligne après chaque troisième produit -->
                    </c:if>
                </c:forEach>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
