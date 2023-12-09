<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BazarWeb- Accueil</title>
    <style>
   		  html { 
  			background: url(bazar.jpg) no-repeat center center fixed; 
  			-webkit-background-size: cover;
  			-moz-background-size: cover;
  			-o-background-size: cover;
  			background-size: cover;
			
		 }
          body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            max-width: 600px;
            padding: 20px;
            background-color: rgba(0, 0, 0, 0.8);
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #ffffff;
        }
        p {
            color: #ffffff;
        }
        form {
            margin-top: 20px;
        }
        button {
            padding: 10px 20px;
            font-size: 18px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }

       
        .container::before {
            content: "";
            position: absolute;
            top: -50px;
            left: -50px;
            right: -50px;
            bottom: -50px;
            z-index: -1;
        }

        .container::after {
            content: "";
            position: absolute;
            top: -50px;
            left: -50px;
            right: -50px;
            bottom: -50px;
            z-index: -1;
       
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Bienvenue sur BazarWeb</h1>
        <p>Votre commerce en ligne pour des achats passionnants !</p>
        
        <form action="Client" method="get">
            <button type="submit">Accéder en tant que Client</button>
        </form>


        
         <form action="Moderateur" method="get">
            <button type="submit">Accéder en tant que modérateur</button>
        </form>
        
        <form action="InscriptionModerateur" method="get">
            <button type="submit">S'inscrire en tant que modérateur</button>
        </form>
        
        <form action="InscriptionClient" method="get">
            <button type="submit">S'inscrire en tant que Client</button>
        </form>
    </div>
</body>
</html>
