package bean;

public class Commande {
int userId;
String productName;
String description;
double prixUnitaire;
int quantite;
double prixTotal;
String date;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrixUnitaire() {
	return prixUnitaire;
}
public void setPrixUnitaire(double prixUnitaire) {
	this.prixUnitaire = prixUnitaire;
}
public int getQuantite() {
	return quantite;
}
public void setQuantite(int quantite) {
	this.quantite = quantite;
}
public double getPrixTotal() {
	return prixTotal;
}
public void setPrixTotal(double prixTotal) {
	this.prixTotal = prixTotal;
}
public Commande(int userId, String productName, String description, double prixUnitaire, int quantite,
		double prixTotal,String date) {
	
	this.userId = userId;
	this.productName = productName;
	this.description = description;
	this.prixUnitaire = prixUnitaire;
	this.quantite = quantite;
	this.prixTotal = prixTotal;
	this.date=date;
}
	
	
}
