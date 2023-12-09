package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paniers {

	 	public Paniers() {
		super();
	}
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long panier_id;
	 	
	 	private Long user_id;
	 	private Long product_id;
	 	int quantity;
		public Long getUser_id() {
			return user_id;
		}
		public void setUser_id(Long user_id) {
			this.user_id = user_id;
		}
		public Long getProduct_id() {
			return product_id;
		}
		public void setProduct_id(Long product_id) {
			this.product_id = product_id;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public Paniers(Long user_id, Long product_id, int quantity) {
			super();
			this.user_id = user_id;
			this.product_id = product_id;
			this.quantity = quantity;
		}
	 	
	 	
	 	
}
