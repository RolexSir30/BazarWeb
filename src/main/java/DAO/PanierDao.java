package DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Paniers;
import entity.Products;

public class PanierDao {

	public static void main(String[] args) {
		//Paniers p = new Paniers((long) 35,(long) 37, 2);
		
		//addPanier(p);
		List<Paniers> productspaniers= retournerProduitsDuPaniers2(33);
	
		for(Paniers p :productspaniers ) {
			System.out.println(p.getQuantity());
		}
	}
	
	
	
	
    private static final Session session = HibernateSessionFactory.getSession();

    public static void addPanier(Paniers panier) {
        Transaction tx = session.beginTransaction();
        session.save(panier);
        tx.commit();
    }

    public void updatePanier(Paniers panier) {
        Transaction tx = session.beginTransaction();
        session.update(panier);
        tx.commit();
    }

    public void deletePanier(Long panierId) {
        Transaction tx = session.beginTransaction();
        Paniers panier = session.get(Paniers.class, panierId);
        if (panier != null) {
            session.delete(panier);
        }
        tx.commit();
    }

    public List<Paniers> getAllPaniers() {
        Query<Paniers> query = session.createQuery("FROM Paniers", Paniers.class);
        return query.list();
    }

    public Paniers getPanierById(Long panierId) {
        return session.get(Paniers.class, panierId);
    }

    public void closeSession() {
        session.close();
    }

	public void ViderPanier(int idproduit, int iduser) {
		// TODO Auto-generated method stub
		
		
		
	}

	public static void RemoveProduct(long idproduit, long iduser) {
		// TODO Auto-generated method stub
		 Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Paniers.class);

	        try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
	            Transaction tx = session.beginTransaction();

	            // Utilisez HQL pour supprimer le produit en fonction de son ID
	            Query query = session.createQuery("DELETE FROM Paniers WHERE product_id = :product_id AND user_id=:iduser");
	            query.setParameter("product_id", (long) idproduit);
	            query.setParameter("iduser", (long) iduser);

	            
	            
	            int rowCount = query.executeUpdate();
	            System.out.println("Rows affected: " + rowCount);

	            tx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
		
	}

	public static List<Products> retournerProduitsDuPaniers(long idUsername) {
		// TODO Auto-generated method stub
		// Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Paniers.class);
		
		Configuration con = new Configuration()
		        .configure()
		        .addAnnotatedClass(Products.class)
		        .addAnnotatedClass(Paniers.class);
		
		SessionFactory sf =con.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx =session.beginTransaction();

		  String hql = "SELECT p FROM Products p JOIN Paniers pa ON p.product_id = pa.product_id WHERE pa.user_id = :userId";
		  Query<Products> query = session.createQuery(hql, Products.class);
		  query.setParameter("userId", idUsername);
		  return query.list();
		 }
	    
	public static List<Paniers> retournerProduitsDuPaniers2(long idUsername) {
		// TODO Auto-generated method stub
		// Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Paniers.class);
		
		Configuration con = new Configuration()
		        .configure()
		        .addAnnotatedClass(Products.class)
		        .addAnnotatedClass(Paniers.class);
		
		SessionFactory sf =con.buildSessionFactory();
		
		Session session = sf.openSession();
		
		Transaction tx =session.beginTransaction();

		  String hql = "SELECT pa FROM Products p JOIN Paniers pa ON p.product_id = pa.product_id WHERE pa.user_id = :userId";
		  Query<Paniers> query = session.createQuery(hql, Paniers.class);
		  query.setParameter("userId", idUsername);
		  return query.list();
		 }

	
	
	
	public double calculPrixPanier(ArrayList<Products> panier,ArrayList<Paniers> quantite) {
		// TODO Auto-generated method stub
		int prixTotal=0;
		int size=panier.size();
		for(int i =0 ;i<size;i++) {
			prixTotal+=panier.get(i).getPrice()*quantite.get(i).getQuantity();
		}
		return prixTotal;
	}
}
