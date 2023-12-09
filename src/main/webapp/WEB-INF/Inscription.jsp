<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription Client</title>
    <style>
         body {
    background-color: #232f3e;
    color: #fff;
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
}

header {
    background-color: #131a22;
    padding: 10px 0;
    text-align: center;
}


h1 {
    color: #ff9900;
    margin: 20px 0;
    font-family: 'Roboto', sans-serif; /* Nouvelle police sans-serif */
}
form {
    max-width: 600px;
    margin: 20px auto;
    padding: 20px;
    background-color: #131a22;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

label {
    display: block;
    margin-top: 10px;
    color: #ff9900;
    text-align:center;
}

input[type="text"],
input[type="email"],
input[type="password"] {
    width: 50%;
    padding: 10px;
    margin-left :25%;
    margin-right :25%;
    margin-top: 5px;
    background-color: #232f3e;
    border: none;
    border-radius: 5px;
    color: #fff;
}

input[type="submit"] {
    width: 100%;
    padding: 10px;
    margin-top: 10px;
    background-color: #ff9900;
    border: none;
    border-radius: 5px;
    color: #232f3e;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #ff7700;
}

    </style>
</head>
<body>
<h1>Inscrivez-vous en tant que Client :</h1>
<form action="InscriptionClient" method="post"onsubmit="return doublemotdepasseegaux()">
    <label for="username">Nom d'utilisateur :</label>
    <input type="text" id="username" name="username" required><br>

    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password" required><br>
    
    <label for="password2">Retapez votre mot de passe:</label>
    <input type="password" id="password2" name="password2" required><br>

    <label for="email">Email :</label>
    <input type="email" id="email" name="email" required><br>

    <label for="address">Adresse :</label>
    <input type="text" id="address" name="address" required><br>

    <label for="postalCode">Code Postal :</label>
    <input type="text" id="postalCode" name="postalCode" required><br>

    <input type="submit" value="S'inscrire">
</form>

<script>
    function doublemotdepasseegaux() {
        var password = document.getElementById('password');
        var password2 = document.getElementById('password2');

        if (password.value !== password2.value) {
            alert("Les mots de passe ne correspondent pas.");
            return false;
        }

        return true;
    }
</script>
</form>
</body>
</html>
