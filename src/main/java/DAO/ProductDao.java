package DAO;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Products;

public class ProductDao {

	
	public static void main(String[] args) {
		
		//System.out.println(rechercheproduitModo("Amazon"));
		//for(Products p :rechercheproduitModo("Amazon") ) {
		//	System.out.println(p.getName());
		//}
		//System.out.println(a.getStockQuantity());
	       // Products product = new Products("jl", "jl", 15, 15, "j", "j", "admin");
			//ajouterProduit(product);
			ArrayList<Products> po = rechercheproduit("a");
			System.out.println(po);
			for(Products p :po) {
				System.out.println(p.getName());
			}
			
			
	}
	
	public static void deleteProductsByUsername(String username) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			// Utilisez HQL pour supprimer l'utilisateur en fonction de son nom
			// d'utilisateur
			Query query = session.createQuery("DELETE FROM Products WHERE username_modo = :username");
			query.setParameter("username", username);

			int rowCount = query.executeUpdate();
			System.out.println("Rows affected: " + rowCount);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Gérez les exceptions selon vos besoins
		}
	}
	
    public static void ajouterProduit(Products product) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);

        try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            System.out.print("Adding product...");
            session.save(product);
            System.out.println("Product added successfully.");
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void supprimerProduitDelaBDD(int productId) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);

        try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            // Utilisez HQL pour supprimer le produit en fonction de son ID
            Query query = session.createQuery("DELETE FROM Products WHERE product_id = :productId");
            query.setParameter("productId", (long) productId);
            
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean productExists(String productName) {
        Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);

        try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();

            // Utilisez HQL pour compter le nombre d'occurrences du produit en fonction de son nom
            Query query = session.createQuery("SELECT COUNT(*) FROM Products WHERE name = :productName");
            query.setParameter("productName", productName);

            Long count = (Long) query.uniqueResult();

            tx.commit();

            // Retournez true si le nombre d'occurrences est supérieur à zéro (le produit existe déjà)
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Vous pouvez ajouter d'autres méthodes pour la lecture, la mise à jour, etc., selon vos besoins.

    public ProductDao() {
        super();
    }



	public void supprimerUnElementDuStock(int productId) {
		// TODO Auto-generated method stub
		 Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);

		    try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
		        Transaction tx = session.beginTransaction();

		        Query query = session.createQuery("UPDATE Products SET stock_quantity = stock_quantity - 1 WHERE product_id = :productId AND stock_quantity > 0");
		        query.setParameter("productId", (long) productId);

		        int rowCount = query.executeUpdate();
		        System.out.println("Rows affected: " + rowCount);

		        tx.commit();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
	}



	public static ArrayList<Products> rechercheproduit(String string) {
		 ArrayList<Products> resultList = new ArrayList<>();

		    Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);

		    try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
		        Transaction tx = session.beginTransaction();

		        // Utilize HQL to select products with a name containing the specified string
		        Query<Products> query = session.createQuery("FROM Products WHERE name LIKE :searchString", Products.class);
		        query.setParameter("searchString", "%" + string + "%");

		        resultList = (ArrayList<Products>) query.list();

		        tx.commit();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return resultList;
	}



	public static Products getProduct(long product_id) {
		Products produit = new Products();
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Products WHERE product_id = :produc_id");
        query.setParameter("produc_id", product_id);
        produit = (Products) ((org.hibernate.query.Query) query).uniqueResult();
		
		
		
		return produit;
	}



	public static ArrayList<Products> rechercheproduitModo(String username) {
		 ArrayList<Products> produits = new ArrayList<>();

		    try (SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class).buildSessionFactory();
		         Session session = sf.openSession()) {

		        Transaction tx = session.beginTransaction();
		        Query<Products> query = session.createQuery("FROM Products WHERE username_modo = :modo", Products.class);
		        query.setParameter("modo", username);

		        List<Products> resultList = query.list();
		        produits.addAll(resultList);

		        tx.commit();
		    } catch (HibernateException e) {
		        e.printStackTrace(); // Gestion appropriée de l'exception
		    }

		    return produits;
	}

	public static ArrayList<Products> rechercheproduitParNomEtModo(String partialName, String username) {
	    ArrayList<Products> produits = new ArrayList<>();

	    try (SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Products.class).buildSessionFactory();
	         Session session = sf.openSession()) {

	        Transaction tx = session.beginTransaction();
	        Query<Products> query = session.createQuery("FROM Products WHERE username_modo = :username AND name LIKE :partialName", Products.class);
	        query.setParameter("username", username);
	        query.setParameter("partialName", "%" + partialName + "%");

	        List<Products> resultList = query.list();
	        produits.addAll(resultList);

	        tx.commit();
	    } catch (HibernateException e) {
	        e.printStackTrace(); // Gestion appropriée de l'exception
	    }

	    return produits;
	}


	


}
