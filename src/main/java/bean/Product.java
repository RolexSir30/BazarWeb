package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Product {
	
	/**
	 * 
	 * 
	 * Cette classe bean va nous permettre d ajouter la quantite ds le panier et les attributs 
	 * du produits dans une meme classe qu'on manipulera dans le fichier sql à l'aide de 
	 * jdbc pour plus de simplicité.
	 * 
	 */
	
	
    int user;
    String usernameModo;
	public Product(int user,  int quantite, int id, String name, String description, double price,
			int stock_quantity, String category_id, String image,String usernameModo) {
		super();
		this.user = user;
		this.quantite = quantite;
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock_quantity = stock_quantity;
		this.category_id = category_id;
		this.image = image;
		this.usernameModo = usernameModo;

	}
	public String getUsernameModo() {
		return usernameModo;
	}
	public void setUsernameModo(String usernameModo) {
		this.usernameModo = usernameModo;
	}
	public Product(int id, String name, double price, int stock_quantity,
			String category_id, String image,int quantite, String description) {
		
		this.id=id;
		this.quantite = quantite;
		this.name = name;
		this.price = price;
		this.stock_quantity = stock_quantity;
		this.image = image;
		this.description=description;
	}
    public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	int quantite;

	int id;
    String name ;
    String description;
    double price;
    int stock_quantity ;
    String category_id ;
    String image;
	public Product(String name, String description, double price2, int stock_quantity2, String category_id, String image) {
		this.name = name;
		this.description = description;
		this.price = price2;
		this.stock_quantity = stock_quantity2;
		this.category_id = category_id;
		this.image = image;
	}
	public Product(int id,String name, String description, double price2, int stock_quantity2, String category_id, String image) {
		this.name = name;
		this.id = id;
		this.description = description;
		this.price = price2;
		this.stock_quantity = stock_quantity2;
		this.category_id = category_id;
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Product(int id2, String name, String description, int stock_quantity) {
		id=id2;
		this.name = name;
		this.description = description;
		this.stock_quantity = stock_quantity;
	}


	public Product(String productName, String productDescription, double productPrice, int stockQuantity,
			String categoryId, String imageLink, String usernameModo2) {
		// TODO Auto-generated constructor stub
		this.name=productName;
		this.description=productDescription;
		this.price=productPrice;
		this.stock_quantity=stockQuantity;
		this.category_id=categoryId;
		this.image=imageLink;
		this.usernameModo=usernameModo2;
				
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	public String getCategory(int id) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce2023",SQL.userSQL,SQL.passwordSQL);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM categories where category_id="+id);
			
			while(result.next()) {
				String name = result.getString("name");
			}
			}catch(Exception e ) {
				
			}
			
			
			return name;
			
		};
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_id() {
		
		return category_id;
	}

    
    
	
}
