<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BazarWeb - Client</title>
    <style>
        body {
            background-color: black;
            color: #fff; /* Updated text color to white */
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        button {
            background-color: #ff9900;
            color: #fff;
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

        h1, h2 {
            color: #ff9900;
            margin: 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #333; /* Updated border color to dark gray */
        }

        th, td {
            padding: 15px;
            text-align: left;
            color: #ff9900; /* Updated text color to orange */
        }

        th {
            background-color: #1a1a1a; /* Updated background color to dark gray */
        }

        tr:nth-child(even) {
            background-color: #333; /* Updated even row background color to darker gray */
        }

        tr:nth-child(odd) {
            background-color: #2c2c2c; /* Updated odd row background color to even darker gray */
        }

        img.product-image {
            max-width: 100px;
            max-height: 100px;
        }

        #user-info {
            text-align: right;
            margin-top: 10px;
        }

        #user-info p {
            color: #ff9900;
            margin: 0;
        }

        .container {
            margin: 0 auto;
            max-width: 1200px;
            background-color: #1a1a1a; /* Updated container background color to dark gray */
            padding: 20px;
        }

        .top-right-form {
            text-align: right;
            margin-top: 10px;
        }

        #banner {
            background-color: #131a22;
            padding: 10px;
            text-align: center;
            color: #ffffff;
        }

        #banner h1 {
            margin: 0;
        }

        #logo {
            width: 50px;
            height: auto;
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div id="banner">
        <img id="logo" src="https://png.pngtree.com/png-clipart/20220627/original/pngtree-aladdin-lamp-composition-with-luminous-particles-png-image_8207950.png" alt="BazarWeb Logo">
        <h1>BazarWeb</h1>
    </div>
    
    <div class="top-right-form">
        <form action="Accueil" method="get">
            <button type="submit">Déconnexion</button>
        </form>
    </div>

    <div class="container">
        <%
            String username = (String) session.getAttribute("username");
            Object s =  session.getAttribute("pt_fidelite");
            int pt_Fidelite=(int) s;
        %>

        <div id="user-info">
                <p>Bienvenue, ${username} !</p>
                <p>Vos points de fidélité : <%= pt_Fidelite %> pts </p>
        </div>

        <h2>Nos produits</h2>

        <form action="PanierVue" method="post">
            <input type="hidden" name="username" value="<%= username %>">
            <button type="submit">Panier</button>
        </form>

        <div style="margin-top: 10px;"></div>

        <form action="HistoriqueCommande" method="get">
            <input type="hidden" name="userId" value="${userId}">
            <button type="submit">Historique des commandes</button>
        </form>

        <table>
            <tr>
                <th>Produits</th>
            </tr>
            <tr>
                <td>
                    <div>
                        <c:forEach items="${products}" var="product" varStatus="loop">
                            <div style="display: inline-block; margin: 10px; text-align: center; width: 30%;">
                                <a href="InfoProduct?productId=${product.product_id}&username=<%= username %>"
                                   style="text-decoration:none;color:#ff9900;">
                                    <img src="${product.image}" alt="" width="200" height="200">
                                    <p>${product.name}</p>
                                    <p>${product.price} €</p>
                                </a>
                            </div>
                            <c:if test="${loop.index % 3 == 2}">
                                <br>
                            </c:if>
                        </c:forEach>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
