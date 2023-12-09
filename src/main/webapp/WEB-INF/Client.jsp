<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Connexion Client</title>
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

<div id="banner" style="background-color: #1a1a1a;
            padding: 10px;
            text-align: center;">
    <img id="logo" style= "     width: 50px; 
            height: auto;
            margin-right: 10px;" src="https://png.pngtree.com/png-clipart/20220627/original/pngtree-aladdin-lamp-composition-with-luminous-particles-png-image_8207950.png" alt="BazarWeb Logo">
    <h1>BazarWeb</h1>
</div>

<h1>Connexion Client</h1>

<form method="post" action="Client">
    <label for="username">Nom d'utilisateur :</label>
    <input type="text" id="username" name="username">
    
    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password">
    
    <input type="submit" value="Se connecter">
</form>

</body>
</html>
