<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profil Utilisateur</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    .container {
      width: 80%;
      margin: 0 auto;
    }




    .user-container {
      background-color: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      margin-top: 50px;
      animation: fadeIn 1s ease-in-out;
    }

    .user-details {
      margin-bottom: 20px;
    }

    .user-details label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    .user-details span {
      display: block;
      margin-bottom: 10px;
    }
  .delete-button {
      background-color: #dc3545; /* Couleur de fond du bouton */
      color: #fff;
      padding: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .delete-button:hover {
      background-color: #c82333;
    }
    
    .delete-form {
      display: inline-block;
      margin-top: 10px;
    }

    /* Ajoutez d'autres animations CSS selon vos besoins */
    @keyframes fadeIn {
      from {
        opacity: 0;
      }
      to {
        opacity: 1;
      }
    }
  </style>
</head>
<body>
  <div class="container">
    <div class="user-container">
      <div class="user-details">
        <label for="username">Nom d'utilisateur:</label>
        <span>${user.getUsername()}</span>
      </div>
      <div class="user-details">
        <label for="password">Mot de passe:</label>
        <span>${user.password}</span>
      </div>
      <div class="user-details">
        <label for="email">Email:</label>
        <span>${user.email}</span>
      </div>
      <div class="user-details">
        <label for="address">Adresse:</label>
        <span>${user.adresse}</span>
      </div>
      <div class="user-details">
        <label for="postalCode">Code postal:</label>
        <span>${user.code_postal}</span>
      </div>
    </div>
     <form class="delete-form" action="SupprimerUser" method="post">
        <input type="hidden" name="username" value="${user.getUsername()}">
        <button class="delete-button" type="submit">Supprimer l'utilisateur de la base de donnée </button>
      </form>
  </div>
  
 
  
  
</body>
</html>