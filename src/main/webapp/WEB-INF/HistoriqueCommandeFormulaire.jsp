<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connection : Historique de commande</title>
    <style>
        body {
            background-color: black;
            color: white;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 300px;
            margin: 100px auto;
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: orange;
            color: black;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="HistoriqueCommande" method="post">
            <h2>Historique de Commande</h2>


            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="hidden" id="userId" name="userId" value=${userId}>

            <input type="submit" value="Voir l'historique de commande">
        </form>
    </div>
</body>
</html>
