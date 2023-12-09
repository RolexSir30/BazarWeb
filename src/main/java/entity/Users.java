package entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String adresse;
    private String code_postal;
    private int pt_fidelite;

    // Ajoutez les constructeurs, les getters et les setters selon vos besoins.

    // Constructeur par défaut
    public Users() {
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public int getPt_fidelite() {
		return pt_fidelite;
	}

	public void setPt_fidelite(int pt_fidelite) {
		this.pt_fidelite = pt_fidelite;
	}

	// Constructeur avec tous les champs
    public Users(String username, String password, String email, String role, String adresse, String code_postal, int pt_fidelite) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.adresse = adresse;
        this.code_postal = code_postal;
        this.pt_fidelite = pt_fidelite;
    }

    // Ajoutez les getters et les setters pour chaque champ

    public Users(String username2, String password2) {
		// TODO Auto-generated constructor stub
    	this.username=username2;
    	this.password=password2;
	}

	// Exemple de getter et setter pour user_id
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
