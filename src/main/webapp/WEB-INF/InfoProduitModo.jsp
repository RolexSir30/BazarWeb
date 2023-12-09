<%@ page import="bean.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BazarWeb - Détails du Produit</title>
    <style>
        body {
            background-color: #333;
            color: #fff;
            font-family: Arial, sans-serif;
        }

        h1, h2 {
            color: #ff9900;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #555;
        }

        th, td {
            padding: 10px;
        }

        th {
            background-color: #444;
        }

        tr:nth-child(even) {
            background-color: #444;
        }

        tr:nth-child(odd) {
            background-color: #333;
        }

        img.product-image {
            max-width: 300px;
            max-height: 300px; 
        }

        #user-info {
            text-align: right;
        }

        #user-info p {
            color: #ff9900;
        }

        form {
            max-width: 50px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #ff9900;
        }

        input[type="text"],
        input[type="password"],
        input[type="number"] {
            width: fit-content;
            padding: 10px;
            margin-top: 5px;
            background-color: #444;
            border: none;
            border-radius: 5px;
            color: #fff;
        }

        input[type="submit"] {
            width: fit-content;
            padding: 10px;
            margin-top: 10px;
            background-color: #ff9900;
            border: none;
            border-radius: 5px;
            color: #333;
            font-weight: bold;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #ff7700;
        }
        
        button {
            background-color: #ff9900;
            color: #333;
            padding: 10px 20px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #ffcc66;
        }
    </style>
</head>
<body>
<div id="banner" style="background-color: #1a1a1a; padding: 10px; text-align: center;">
    <img id="logo" style="width: 50px; height: auto; margin-right: 10px;" src="https://png.pngtree.com/png-clipart/20220627/original/pngtree-aladdin-lamp-composition-with-luminous-particles-png-image_8207950.png" alt="BazarWeb Logo">
    <h1>BazarWeb</h1>
</div>
<%
    // Récupérer la valeur de la variable depuis la requête
    String username = request.getParameter("username");
%>
<form style="text-align: right;max-width: 100%;" action="Accueil" method="get">
    <button type="submit">Déconnexion</button>
</form>
<div class="container">
    <!-- Affichage des informations du client -->
    <div id="user-info" style="text-align: right;">
        <p>Bienvenue, <%= username %> !</p>
    </div>
    <h2>Détails du Produit</h2>
 <table>
            <tr>
                <th>Produit</th>
            </tr>
            <tr>
                <td>
                    <div style="text-align: center;">
                        <img class="product-image" src="${product.image}" alt="${product.name}">
                        <p>Nom : ${product.name}</p>
                        <p>Prix : ${product.price} €</p>
                        <p>Description : ${product.description}</p>
                        <p>Quantité en Stock : ${product.stock_quantity}</p>
                        <p>Catégorie : ${product.getCategory_id()}</p>
                    </div>
                </td>
            </tr>
            
    </table>
</div>
</body>
</html>
