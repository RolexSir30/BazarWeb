<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Commandes</title>
    <style>
           body {
    background-color: #333;
    color: #fff;
    font-family: 'Arial', sans-serif;
}

h2 {
    color: orange;
}

input[type="submit"] {
    background-color: orange;
    color: white;
    border: none;
    padding: 10px 20px;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: green;
    color: black;
}

table {
    border-collapse: collapse;
    width: 100%;
    margin-top: 20px;
}

th, td {
    border: 1px solid white;
    padding: 10px;
    text-align: left;
}

th {
    background-color: orange;
    color: white;
}

/* Add some styling to alternate table rows */
tbody tr:nth-child(even) {
    background-color: #555;
}

/* Style the date cell separately */
td:nth-child(6) {
    font-style: italic;
    color: #ccc;
}

    </style>
</head>
<body>
    <h2>Liste des Commandes</h2>

    <%-- Récupérer la liste des commandes depuis la servlet --%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="bean.Commande" %>
    <% 
        ArrayList<Commande> commandes = (ArrayList<Commande>) request.getAttribute("commandes");
     %>

    <%-- Vérifier si la liste des commandes n'est pas vide --%>
    <c:if test="${not empty commandes}">
        <table>
            <thead>
                <tr>
                    <th>Produit</th>
                    <th>Description</th>
                    <th>Prix Unitaire</th>
                    <th>Quantité</th>
                    <th>Prix Total</th>
                    <th>Date de la commande :</th>
                    
                </tr>
            </thead>
            <tbody>
                <%-- Itérer sur la liste des commandes pour afficher chaque commande --%>
                <c:forEach var="commande" items="${commandes}">
                    <tr>
                        <td>${commande.productName}</td>
                        <td>${commande.description}</td>
                        <td>${commande.prixUnitaire}</td>
                        <td>${commande.quantite}</td>
                        <td>${commande.prixTotal}</td>
                        <td>${commande.date}</td>
                        
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <form action="Client" method="post">
        <input type="hidden" name="username" value="${username}" />
    	<input type="hidden" name="password" value="${password}" />
        <input type="submit" value="Retourner sur la liste des produits" />
    </form>
    
</body>
</html>
