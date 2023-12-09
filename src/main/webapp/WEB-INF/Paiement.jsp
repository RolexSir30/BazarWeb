<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Paiement</title>
</head>
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    text-align: center;
}

h1 {
    color: #333;
}

form {
    background-color: #fff;
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
    display: block;
    margin-bottom: 5px;
    color: #333;
}

input[type="text"] {
    width: 75%;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

input[type="text"]:focus {
    outline: none;
    border-color: #007bff;
}

input[type="submit"] {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #0056b3;
}

input[type="hidden"] {
    display: none;
}

p {
    font-size: 18px;
    font-weight: bold;
    color: #333;
    margin-bottom: 20px;
}

/* Add a lock icon for the secure payment */
.lock-icon {
    font-size: 30px;
    color: green;
    margin: 10px 0;
}
</style>
<body>
    <h1>Paiement</h1>
    <% double prixTotal = (double) request.getAttribute("prixTotal"); %>
    <%
    
        String username = (String) session.getAttribute("username");
        
    %>
    <%
    
    Object s =  session.getAttribute("pt_fidelite");
    int pt_Fidelite=(int) s ;
        
    %>
    
    <!-- Display the total price -->
    <p>Prix Total: <%= prixTotal %>€  -  <%= ((pt_Fidelite/25) > prixTotal) ? prixTotal :pt_Fidelite/25 %>€  (points de fidélites) </p>
    
    <form action="ValidationPaiement" method="post">
        <!-- Payment information fields -->
        <label for="number">Card Number:</label>
        <input type="text" id="number" name="number" required>
        
        <label for="date">Expiry Date (MM/YY):</label>
        <input type="text" id="date" name="date" required placeholder="MM/YY">

        <label for="cvv">CVV:</label>
        <input type="text" id="cvv" name="cvv" required>
        
        <!-- Hidden inputs to pass user ID and prixTotal -->
        <input type="hidden" name="userId" value="${userId}">
        <input type="hidden" name="pt_fidelite" value="<%= pt_Fidelite %>">
       	<%
   		double pointsDeducted = pt_Fidelite / 25;
    	double prixTotalAfterDeduction = (pointsDeducted > prixTotal) ? 0 : (prixTotal - pointsDeducted);
		%>
		<input type="hidden" name="prixTotal" value="<%= prixTotalAfterDeduction %>">

        
        <input type="submit" value="Payez maintenant">
    </form>
</body>
</html>
