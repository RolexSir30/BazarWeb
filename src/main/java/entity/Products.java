package entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
	private String category_id;

	private String name;
    private String description;
    private double price;
    private int stock_quantity;
    private String image;
    private String username_modo;
    private int quantiteCommandee;

    public int getQuantiteCommandee() {
		return quantiteCommandee;
	}

	public void setQuantiteCommandee(int quantiteCommandee) {
		this.quantiteCommandee = quantiteCommandee;
	}

	public Products(Long product_id, String name, String description, double price, int stockQuantity, String category_id,
			String image, String username_modo) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock_quantity = stock_quantity;
		this.category_id = category_id;
		this.image = image;
		this.username_modo = username_modo;
	}

	public int getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}



	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
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



	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUsername_modo() {
		return username_modo;
	}

	public void setUsername_modo(String username_modo) {
		this.username_modo = username_modo;
	}

    // Ajoutez les constructeurs, les getters et les setters selon vos besoins.

    // Constructeur par défaut
    public Products() {
    }

    // Constructeur avec tous les champs
    public Products(String name, String description, double price, int stock_quantity, String category_id, String image, String username_modo) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.category_id = category_id;
        this.image = image;
        this.username_modo = username_modo;
    }


    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    // Ajoutez les autres getters et setters...

}
