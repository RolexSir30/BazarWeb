package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SQL {

	static String userSQL = "root";
	static String passwordSQL = "";

	public static void ajouterPanier(int productId, Long long1, int quantity) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023", userSQL,
					passwordSQL);

			// Vérifiez la quantité disponible en stock du produit
			PreparedStatement checkStockStatement = connection
					.prepareStatement("SELECT stock_quantity FROM products WHERE product_id = ?");
			checkStockStatement.setInt(1, productId);

			ResultSet stockResult = checkStockStatement.executeQuery();

			if (stockResult.next()) {
				int stockQuantity = stockResult.getInt("stock_quantity");
				if (quantity > stockQuantity) {
					// Si la quantité commandée est supérieure à la quantité en stock,
					// mettez-la au maximum disponible
					quantity = stockQuantity;
				}
			}

			// Vérifiez si le produit est déjà dans le panier de l'utilisateur
			PreparedStatement checkStatement = connection
					.prepareStatement("SELECT * FROM paniers WHERE user_id = ? AND product_id = ?");
			checkStatement.setLong(1, long1);
			checkStatement.setInt(2, productId);

			ResultSet checkResult = checkStatement.executeQuery();

			if (checkResult.next()) {
				// Le produit est déjà dans le panier, mettez à jour la quantité
				int currentQuantity = checkResult.getInt("quantity");
				int newQuantity = currentQuantity + quantity;

				// Mettez à jour la quantité dans le panier
				PreparedStatement updateStatement = connection
						.prepareStatement("UPDATE paniers SET quantity = ? WHERE user_id = ? AND product_id = ?");
				updateStatement.setInt(1, newQuantity);
				updateStatement.setLong(2, long1);
				updateStatement.setInt(3, productId);

				updateStatement.executeUpdate();
			} else {
				// Le produit n'est pas encore dans le panier, ajoutez-le
				PreparedStatement insertStatement = connection
						.prepareStatement("INSERT INTO paniers (user_id, product_id, quantity) VALUES (?, ?, ?)");
				insertStatement.setLong(1, long1);
				insertStatement.setInt(2, productId);
				insertStatement.setInt(3, quantity);

				insertStatement.executeUpdate();
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	public static ArrayList<Product> retournerProduitsDuPaniers(long idUsername) {
		// TODO Auto-generated method stub

		ArrayList<Product> paniers = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023", userSQL,
					passwordSQL);
			PreparedStatement statementPanierId = connection.prepareStatement(
					"SELECT * FROM products p JOIN paniers pa ON p.product_id = pa.product_id WHERE pa.user_id ="
							+ idUsername);

			ResultSet result = statementPanierId.executeQuery(); // On recupere toutes les infos du paniers

			while (result.next()) {
				int product_id = Integer.parseInt(result.getString("product_id"));
				String name = result.getString("name");
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				int stock_quantity = Integer.parseInt(result.getString("stock_quantity"));
				String image = result.getString("image");
				String category_id = result.getString("category_id");
				int quantity = Integer.parseInt(result.getString("quantity"));
				Product produit = new Product(product_id, name, price, stock_quantity, category_id, image, quantity,
						description);
				paniers.add(produit);

			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return paniers;

	}

	

	public static void updateQuantity(int userId) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023", userSQL,
					passwordSQL);

			// Sélectionnez les produits dans le panier de l'utilisateur
			PreparedStatement selectStatement = connection
					.prepareStatement("SELECT p.product_id, p.stock_quantity, pa.quantity " + "FROM products p "
							+ "JOIN paniers pa ON p.product_id = pa.product_id "
							+ "JOIN users u ON u.user_id = pa.user_id " + "WHERE u.user_id = ?");
			selectStatement.setInt(1, userId);

			ResultSet result = selectStatement.executeQuery();

			if (!result.isBeforeFirst()) {
				System.out.println("No rows found for user ID: " + userId);
			}

			while (result.next()) {
				int productId = result.getInt("product_id");
				int stockQuantity = result.getInt("stock_quantity");
				int quantityInCart = result.getInt("quantity");

				int newStockQuantity = stockQuantity - quantityInCart;
				System.out.println("stock = " + newStockQuantity);
				// Mettez à jour la base de données avec la nouvelle quantité en stock
				PreparedStatement updateStatement = connection
						.prepareStatement("UPDATE products SET stock_quantity = ? WHERE product_id = ?");
				updateStatement.setInt(1, newStockQuantity);
				updateStatement.setInt(2, productId);

				updateStatement.executeUpdate();
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public static void ajouterPtFidelite(int idUsername, double points) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023", userSQL,
					passwordSQL);

			// Vérifiez si l'utilisateur existe
			PreparedStatement checkUserStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
			checkUserStatement.setInt(1, idUsername);

			ResultSet userResult = checkUserStatement.executeQuery();

			if (userResult.next()) {
				// L'utilisateur existe, mettez à jour les points de fidélité
				int currentPoints = userResult.getInt("pt_fidelite");
				double newPoints = currentPoints + points;

				// Arrondir les nouveaux points à un chiffre rond
				newPoints = Math.round(newPoints);

				// Mettez à jour la base de données avec les nouveaux points de fidélité
				PreparedStatement updateStatement = connection
						.prepareStatement("UPDATE users SET pt_fidelite = ? WHERE user_id = ?");
				updateStatement.setInt(1, (int) newPoints);
				updateStatement.setInt(2, idUsername);
				updateStatement.executeUpdate();
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void supprimerPtFidelite(int idUsername, double pointsToRemove) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023", userSQL,
					passwordSQL);

			// Vérifiez si l'utilisateur existe
			PreparedStatement checkUserStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
			checkUserStatement.setInt(1, idUsername);

			ResultSet userResult = checkUserStatement.executeQuery();

			if (userResult.next()) {
				// L'utilisateur existe, récupérez les points de fidélité actuels
				int currentPoints = userResult.getInt("pt_fidelite");

				// Vérifiez si l'utilisateur a suffisamment de points pour retirer
				if (currentPoints >= pointsToRemove) {
					// Retirez les points de fidélité
					double newPoints = currentPoints - pointsToRemove;

					// Mettez à jour la base de données avec les nouveaux points de fidélité
					PreparedStatement updateStatement = connection
							.prepareStatement("UPDATE users SET pt_fidelite = ? WHERE user_id = ?");
					updateStatement.setInt(1, (int) newPoints);
					updateStatement.setInt(2, idUsername);

					updateStatement.executeUpdate();
				} else {
					// L'utilisateur n'a pas suffisamment de points, gérer l'erreur ou le cas
					// limite ici
					System.out.println("L'utilisateur n'a pas suffisamment de points.");
				}
			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void AjouterPanierCommande(int idUsername, ArrayList<Product> panierPaye) {
		// TODO Auto-generated method stub

		for (Product p : panierPaye) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023",
						userSQL, passwordSQL);
				String productName = p.getName();
				String description = p.getDescription();
				double prixUnitaire = p.getPrice();
				int quantite = p.getQuantite();
				double prixTotal = prixUnitaire * quantite;
				Date date1 = new Date();

				// Cr�er un objet SimpleDateFormat pour formater la date
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

				// Formatter la date en tant que cha�ne de caract�res
				String date = dateFormat.format(date1);

				System.out.println("Sql.ajouterpaniercommande" + "idusername : " + idUsername + "Nom du produit: "
						+ productName + ", Description: " + description + ", Prix unitaire: " + prixUnitaire
						+ ", Quantit�: " + quantite + ", Prix total: " + prixTotal);
				System.out.println("date :" + date);
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO commande(userId,productName,description,prixUnitaire,quantite,prixTotal,date) VALUES (?, ?, ?, ? ,?, ?,?)");
				statement.setInt(1, idUsername);
				statement.setString(2, productName);
				statement.setString(3, description);
				statement.setDouble(4, prixUnitaire);
				statement.setInt(5, quantite);
				statement.setDouble(6, prixTotal);
				statement.setString(7, date);

				statement.executeUpdate();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();

			}

		}

	}

	public static ArrayList<Commande> retournerProduitsCommandes(int userId) {
		// TODO Auto-generated method stub
		ArrayList<Commande> commandes = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023", userSQL,
					passwordSQL);
			PreparedStatement statementPanierId = connection.prepareStatement("SELECT * FROM commande where userId =" + userId);

			ResultSet result = statementPanierId.executeQuery(); // On recupere toutes les infos du paniers

			while (result.next()) {
				String productName = result.getString("productName");
				String description = result.getString("description");
				double prixUnitaire = result.getDouble("prixUnitaire");
				int quantite = result.getInt("quantite");
				double prixTotal = result.getDouble("prixTotal");
				String date = result.getString("date");
				Commande commande = new Commande(userId, productName, description, prixUnitaire, quantite, prixTotal,
						date);

				commandes.add(commande);

			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return commandes;

	}

}
