package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import classe.SQL;
import entity.Users;

public class UserDao {

	// private EntityManagerFactory entityManagerFactory =
	// Persistence.createEntityManagerFactory("yourPersistenceUnitName");

	public static void main(String[] args) {
		int intValue = 5 /* votre valeur int */;
		long longValue = intValue;
		System.out.println(connectionClient((long) 5,"Saarhan"));
		
		//System.out.println("k"+getPointsDeFidelite("Mato"));
		int a=5;int b=10;
		ajouterPtFidelite( a,b );
		
		
	}

	public void addUser(Users user) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		SessionFactory sf = con.buildSessionFactory();

		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		System.out.print("j");
		session.save(user);
		System.out.print("aj");

		tx.commit();
	}

	public void deleteUserByUsername(String username) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			// Utilisez HQL pour supprimer l'utilisateur en fonction de son nom
			// d'utilisateur
			Query query = session.createQuery("DELETE FROM Users WHERE username = :username");
			query.setParameter("username", username);

			int rowCount = query.executeUpdate();
			System.out.println("Rows affected: " + rowCount);

			tx.commit();
			ProductDao.deleteProductsByUsername(username);
			PanierDao.deletePaniersByUsername(username);
			SQL.deleteCommandesByUsername(username);
			
		} catch (Exception e) {
			e.printStackTrace();
			// Gérez les exceptions selon vos besoins
		}
	}
	
	


	public boolean connectionClient(String username, String password) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			Query query = session.createQuery("FROM Users WHERE username = :username");
			query.setParameter("username", username);

			Users user = (Users) ((org.hibernate.query.Query) query).uniqueResult();

			if (user != null && user.getPassword().equals(password) && "client".equals(user.getRole())) {
				tx.commit();

				return true; 
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false; 
	}
	
	
	
	public static void ajouterPtFidelite(int a, double pt_fidelite_ajout) {
		


		 Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);
	        SessionFactory sf = con.buildSessionFactory();
	        Session session = sf.openSession();
	        Transaction tx = session.beginTransaction();

	        // Utiliser une requête de mise à jour pour ajouter des points à pt_fidelite
	        Query query = session.createQuery("UPDATE Users SET pt_fidelite = pt_fidelite + :points WHERE user_id = :idUsername");
	        query.setParameter("points", pt_fidelite_ajout);
	        query.setParameter("idUsername", a);
	        
	        query.executeUpdate();

	        tx.commit();
	        session.close();
		
		
	}

	public static boolean connectionModo(String username, String password) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			// Utilisez HQL pour récupérer l'utilisateur en fonction du nom d'utilisateur
			Query query = session.createQuery("FROM Users WHERE username = :username");
			query.setParameter("username", username);

			Users user = (Users) ((org.hibernate.query.Query) query).uniqueResult();

			// Vérifiez si l'utilisateur existe et si le mot de passe correspond
			if (user != null && user.getPassword().equals(password) && "moderateur".equals(user.getRole())) {
				tx.commit();

				return true; // Nom d'utilisateur et mot de passe corrects
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Gérez les exceptions selon vos besoins
		}

		return false; // Nom d'utilisateur ou mot de passe incorrects
	}

	public boolean UsernameExist(String username) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			// Utilisez HQL pour compter le nombre d'occurrences de l'utilisateur en
			// fonction du nom d'utilisateur
			Query query = session.createQuery("SELECT COUNT(*) FROM Users WHERE username = :username");
			query.setParameter("username", username);

			Long count = (Long) ((org.hibernate.query.Query) query).uniqueResult();

			tx.commit();

			// Retournez true si le nombre d'occurrences est supérieur à zéro (utilisateur
			// existe déjà)
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			// Gérez les exceptions selon vos besoins
		}

		return false; // En cas d'erreur ou d'exception, considérez que l'utilisateur n'existe pas
	}

	public UserDao() {
		super();
	}

	public boolean connectionAdmin(String username, String password) {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			// Utilisez HQL pour récupérer l'utilisateur en fonction du nom d'utilisateur
			Query query = session.createQuery("FROM Users WHERE username = :username");
			query.setParameter("username", username);

			Users user = (Users) ((org.hibernate.query.Query) query).uniqueResult();

			// Vérifiez si l'utilisateur existe et si le mot de passe correspond
			if (user != null && user.getPassword().equals(password) && "admin".equals(user.getRole())) {
				tx.commit();
				return true; // Nom d'utilisateur et mot de passe corrects
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Gérez les exceptions selon vos besoins
		}

		return false; // Nom d'utilisateur ou mot de passe incorrects
	}

	public static Long rechercheIdByUsername(String username) {
		// TODO Auto-generated method stub
		Long id = (long) 0;
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select user_id FROM Users WHERE username = :username");
		query.setParameter("username", username); // Remplacez "leNomUtilisateur" par la valeur actuelle

		Object result = ((org.hibernate.query.Query) query).uniqueResult();
		if (result != null) {
			id = (Long) result;
		}

		tx.commit();
		session.close();
		return id;

	}

	public static boolean connectionClient(long userId, String password) {
		// TODO Auto-generated method stub
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);

		try (SessionFactory sf = con.buildSessionFactory(); Session session = sf.openSession()) {
			Transaction tx = session.beginTransaction();

			// Utilisez HQL pour récupérer l'utilisateur en fonction du nom d'utilisateur
			Query query = session.createQuery("FROM Users WHERE user_id = :userId");
			query.setParameter("userId", userId);

			Users user = (Users) ((org.hibernate.query.Query) query).uniqueResult();

			// Vérifiez si l'utilisateur existe et si le mot de passe correspond
			if (user != null && user.getPassword().equals(password) && "client".equals(user.getRole())) {
				tx.commit();

				return true; // Nom d'utilisateur et mot de passe corrects
			}

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// Gérez les exceptions selon vos besoins
		}

		return false;
	}

	public static entity.Users InfoUser(String username) {
		// TODO Auto-generated method stub
		
		ArrayList<String> clients = new ArrayList<>();
		Users user = new Users();

		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("FROM Users WHERE username = :username");
		query.setParameter("username", username);
		user = (Users) ((org.hibernate.query.Query) query).uniqueResult();

		return user;
	}

	public static ArrayList<String> clients() {
	    ArrayList<Users> users = new ArrayList<>();
	    ArrayList<String> userName = new ArrayList<>();


	    try (SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).buildSessionFactory();
	         Session session = sf.openSession()) {

	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("FROM Users WHERE role = :role", Users.class);
	        query.setParameter("role", "client");

	        List<Users> resultList = ((org.hibernate.query.Query) query).list();
	        users.addAll(resultList);

	        tx.commit();
	    } catch (HibernateException e) {
	        e.printStackTrace(); // Gestion appropriée de l'exception
	    }

	    for( Users u :  users) {
	    	userName.add(u.getUsername());
	    }
	    return userName;
	}

	public static ArrayList<String> moderateurs() {
		// TODO Auto-generated method stub
		ArrayList<String> moderateurs = new ArrayList<>();
		
	    ArrayList<Users> users = new ArrayList<>();
	    ArrayList<String> userName = new ArrayList<>();


	    try (SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class).buildSessionFactory();
	         Session session = sf.openSession()) {

	        Transaction tx = session.beginTransaction();
	        Query query = session.createQuery("FROM Users WHERE role = :role", Users.class);
	        query.setParameter("role", "moderateur");

	        List<Users> resultList = ((org.hibernate.query.Query) query).list();
	        users.addAll(resultList);

	        tx.commit();
	    } catch (HibernateException e) {
	        e.printStackTrace(); // Gestion appropriée de l'exception
	    }

	    for( Users u :  users) {
	    	userName.add(u.getUsername());
	    }
	    return userName;
	}

	public static String RechercherNomFromId(Long i) {
		// TODO Auto-generated method stub
		Long id = (long) 0;
		String username="";
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select username FROM Users WHERE user_id = :id");
		query.setParameter("id", i); // Remplacez "leNomUtilisateur" par la valeur actuelle

		Object result = ((org.hibernate.query.Query) query).uniqueResult();
		if (result != null) {
			username = (String) result;
		}

		tx.commit();
		session.close();
		return username;
		
		
		
		
		
		
		
		
	}
	public static int getPointsDeFidelite(String username) {
		
		int point=-1;
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("select pt_fidelite FROM Users WHERE username = :id");
		query.setParameter("id", username); // Remplacez "leNomUtilisateur" par la valeur actuelle

		Object result = ((org.hibernate.query.Query) query).uniqueResult();
		if (result != null) {
			point = (int) result;
		}

		tx.commit();
		session.close();
		return point;
		
		
		
		
		
		
	}

	public static Users InfoUser(long iduser) {
		// TODO Auto-generated method stub
		
		ArrayList<String> clients = new ArrayList<>();
		Users user = new Users();

		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("FROM Users WHERE user_id = :iduser");
		query.setParameter("iduser", iduser);
		user = (Users) ((org.hibernate.query.Query) query).uniqueResult();

		return user;
	}

	public long RechercheUserId(String username) {
		// TODO Auto-generated method stub
		return 0;
	}



    // No constructor with SessionFactory


	/**
	 * public User getUserById(Long userId) { // EntityManager entityManager =
	 * entityManagerFactory.createEntityManager(); User user = null;
	 * 
	 * try { user = entityManager.find(User.class, userId); } catch (Exception e) {
	 * e.printStackTrace(); } finally { entityManager.close(); }
	 * 
	 * return user; }
	 * 
	 * public List<User> getAllUsers() { EntityManager entityManager =
	 * entityManagerFactory.createEntityManager(); List<User> userList = null;
	 * 
	 * try { userList = entityManager.createQuery("SELECT u FROM User u",
	 * User.class).getResultList(); } catch (Exception e) { e.printStackTrace(); }
	 * finally { entityManager.close(); }
	 * 
	 * return userList; }
	 * 
	 * public void updateUser(User user) { EntityManager entityManager =
	 * entityManagerFactory.createEntityManager(); EntityTransaction transaction =
	 * entityManager.getTransaction();
	 * 
	 * try { transaction.begin(); entityManager.merge(user); transaction.commit(); }
	 * catch (Exception e) { if (transaction.isActive()) { transaction.rollback(); }
	 * e.printStackTrace(); } finally { entityManager.close(); } }
	 * 
	 * public void deleteUser(Long userId) { EntityManager entityManager =
	 * entityManagerFactory.createEntityManager(); EntityTransaction transaction =
	 * entityManager.getTransaction();
	 * 
	 * try { transaction.begin(); User user = entityManager.find(User.class,
	 * userId); if (user != null) { entityManager.remove(user); }
	 * transaction.commit(); } catch (Exception e) { if (transaction.isActive()) {
	 * transaction.rollback(); } e.printStackTrace(); } finally {
	 * entityManager.close(); } }
	 **/
}
