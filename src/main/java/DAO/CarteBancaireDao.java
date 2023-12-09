package DAO;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.CarteBancaire;

public class CarteBancaireDao {

	public static void main(String[] args) {
		System.out.println(updateSolde("7777777", "77/77", 777, 15));
	}
	public static boolean validation(String number, String date, int cvv) {
		Configuration con = new Configuration().configure().addAnnotatedClass(CarteBancaire.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		String hql = "FROM CarteBancaire cb WHERE cb.nombre = :number AND cb.date = :date AND cb.cvv = :cvv";
		List<CarteBancaire> query = session.createQuery(hql, CarteBancaire.class)
		        .setParameter("number", number)
		        .setParameter("date", date)
		        .setParameter("cvv", cvv)
		        .getResultList();


		// If the count is greater than 0, a matching row is found
		return query.size() == 1;
	
	}
	public static boolean updateSolde(String number, String date, int cvv, double prixTotal) {
		Configuration con = new Configuration().configure().addAnnotatedClass(CarteBancaire.class);
		
		SessionFactory sf = con.buildSessionFactory();
		// Retrieve solde from CarteBancaire using Hibernate
		Session session = sf.openSession();

		Query query = session.createQuery(
				"SELECT cb.solde FROM CarteBancaire cb WHERE cb.nombre = :number AND cb.date = :date AND cb.cvv = :cvv"
				);
		query.setParameter("number", number);
		query.setParameter("date", date);
		query.setParameter("cvv", cvv);

		List<Double> soldeList = query.getResultList();

		// Assuming that there is only one result, you can retrieve the solde
		double soldeClient = soldeList.isEmpty() ? 0.0 : soldeList.get(0);

		if ( soldeClient > prixTotal) {
			// Update solde in CarteBancaire using Hibernate
			Transaction transaction = session.beginTransaction();
			Query updateQuery = session.createQuery(
					"UPDATE CarteBancaire SET solde = :newSolde WHERE nombre = :number AND date = :date AND cvv = :cvv");
			updateQuery.setParameter("newSolde", soldeClient - prixTotal);
			updateQuery.setParameter("number", number);
			updateQuery.setParameter("date", date);
			updateQuery.setParameter("cvv", cvv);
			updateQuery.executeUpdate();
			transaction.commit();
			return true;
		} else {
			return false;
		}

	}
	// Other CRUD methods as needed
}
