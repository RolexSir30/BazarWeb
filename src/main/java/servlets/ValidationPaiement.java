package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CarteBancaireDao;
import DAO.PanierDao;
import DAO.UserDao;
import bean.SQL;
import entity.Users;

/**
 * Servlet implementation class ValidationPaiement
 */
@WebServlet("/ValidationPaiement")
public class ValidationPaiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public boolean Validation(HttpServletRequest request, HttpServletResponse response,int userId ) throws IOException {
    	 // RÃ©cupÃ©rez les attributs de la requÃªte
    	PrintWriter out = response.getWriter();
    	double prixTotal = Double.valueOf(request.getParameter("prixTotal"));
	    String number = request.getParameter("number");
	    String date = request.getParameter("date");
	    String cvv = request.getParameter("cvv");
	    CarteBancaireDao cartebancairedao = new CarteBancaireDao();
	    if(cartebancairedao.validation(number,date,Integer.parseInt(cvv))) {
	    	if (cartebancairedao.updateSolde(number,date,Integer.parseInt(cvv),prixTotal)) {
	    		out.println("<html><body>");
		        out.println("<style>body {\r\n" + 
		   	    		"    font-family: Arial, sans-serif;\r\n" + 
		   	    		"    background-color: #f5f5f5;\r\n" + 
		   	    		"    text-align: center;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"h1 {\r\n" + 
		   	    		"    font-size: 24px;\r\n" + 
		   	    		"    color: #333;\r\n" + 
		   	    		"    margin-top: 20px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		".message {\r\n" + 
		   	    		"    background-color: #007bff;\r\n" + 
		   	    		"    color: #fff;\r\n" + 
		   	    		"    padding: 10px;\r\n" + 
		   	    		"    border-radius: 5px;\r\n" + 
		   	    		"    margin-top: 20px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		".error-message {\r\n" + 
		   	    		"    background-color: #ff5733;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"p {\r\n" + 
		   	    		"    font-size: 18px;\r\n" + 
		   	    		"    color: #333;\r\n" + 
		   	    		"    margin-top: 10px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"form {\r\n" + 
		   	    		"    margin-top: 20px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"input[type=\"submit\"] {\r\n" + 
		   	    		"    background-color: #007bff;\r\n" + 
		   	    		"    color: #fff;\r\n" + 
		   	    		"    padding: 10px 20px;\r\n" + 
		   	    		"    border: none;\r\n" + 
		   	    		"    border-radius: 5px;\r\n" + 
		   	    		"    cursor: pointer;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"input[type=\"hidden\"] {\r\n" + 
		   	    		"    display: none;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"</style>");
		        out.println("<h1>Paiement confirmé !!!</h1>");
		        out.println("<p>Vous allez recevoir un mail avec vos informations a jour, merci pour vos achats !</p>");
		        out.println("</body></html>");
		        String historiqueCommandeURL = "HistoriqueCommande";
		        String boutonHistorique = "<br><br><form action='" + historiqueCommandeURL + "' method='get'>"
		                + "<input type='submit' value='Historique des commandes'>"
		        		+"<input type=hidden name='userId' value="+userId+">"
		                + "</form>";
		        
		        String pageCliento = "Client";
		        HttpSession session = request.getSession();

		        // Retrieve the information from the session
		        String username = (String) session.getAttribute("username");
		        String password = (String) session.getAttribute("password");
		        System.out.println("validationpaiement : username :  "+username+" pass : "+password);
		        String pageClient = "<br><form action='" + pageCliento+ "' method='post'>"
		                + "<input type='submit' value='Retourner à la page des produits'>"
		        		+"<input type=hidden name='userId' value="+userId+">"
		               +"<input type=hidden name='username' value="+username+">"
		               +"<input type=hidden name='password' value="+password+">"


		                + "</form>";
		        response.setContentType("text/html");
                out.println(boutonHistorique)	;	  
                out.println(pageClient)	;	        

				return true;
			
			}else {
				out.println("<html><body>");
		        out.println("<style>body {\r\n" + 
		   	    		"    font-family: Arial, sans-serif;\r\n" + 
		   	    		"    background-color: #f5f5f5;\r\n" + 
		   	    		"    text-align: center;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"h1 {\r\n" + 
		   	    		"    font-size: 24px;\r\n" + 
		   	    		"    color: #333;\r\n" + 
		   	    		"    margin-top: 20px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		".message {\r\n" + 
		   	    		"    background-color: #007bff;\r\n" + 
		   	    		"    color: #fff;\r\n" + 
		   	    		"    padding: 10px;\r\n" + 
		   	    		"    border-radius: 5px;\r\n" + 
		   	    		"    margin-top: 20px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		".error-message {\r\n" + 
		   	    		"    background-color: #ff5733;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"p {\r\n" + 
		   	    		"    font-size: 18px;\r\n" + 
		   	    		"    color: #333;\r\n" + 
		   	    		"    margin-top: 10px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"form {\r\n" + 
		   	    		"    margin-top: 20px;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"input[type=\"submit\"] {\r\n" + 
		   	    		"    background-color: #007bff;\r\n" + 
		   	    		"    color: #fff;\r\n" + 
		   	    		"    padding: 10px 20px;\r\n" + 
		   	    		"    border: none;\r\n" + 
		   	    		"    border-radius: 5px;\r\n" + 
		   	    		"    cursor: pointer;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"\r\n" + 
		   	    		"input[type=\"hidden\"] {\r\n" + 
		   	    		"    display: none;\r\n" + 
		   	    		"}\r\n" + 
		   	    		"</style>");
		        out.println("<h1>Erreur de solde</h1>");
		        out.println("<p>Cette carte n'a pas assez d'argent</p>");
		        out.println("</body></html>");
				return false;
			}
	    } else {
	    	out.println("<html><body>");
	        out.println("<style>body {\r\n" + 
	   	    		"    font-family: Arial, sans-serif;\r\n" + 
	   	    		"    background-color: #f5f5f5;\r\n" + 
	   	    		"    text-align: center;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		"h1 {\r\n" + 
	   	    		"    font-size: 24px;\r\n" + 
	   	    		"    color: #333;\r\n" + 
	   	    		"    margin-top: 20px;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		".message {\r\n" + 
	   	    		"    background-color: #007bff;\r\n" + 
	   	    		"    color: #fff;\r\n" + 
	   	    		"    padding: 10px;\r\n" + 
	   	    		"    border-radius: 5px;\r\n" + 
	   	    		"    margin-top: 20px;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		".error-message {\r\n" + 
	   	    		"    background-color: #ff5733;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		"p {\r\n" + 
	   	    		"    font-size: 18px;\r\n" + 
	   	    		"    color: #333;\r\n" + 
	   	    		"    margin-top: 10px;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		"form {\r\n" + 
	   	    		"    margin-top: 20px;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		"input[type=\"submit\"] {\r\n" + 
	   	    		"    background-color: #007bff;\r\n" + 
	   	    		"    color: #fff;\r\n" + 
	   	    		"    padding: 10px 20px;\r\n" + 
	   	    		"    border: none;\r\n" + 
	   	    		"    border-radius: 5px;\r\n" + 
	   	    		"    cursor: pointer;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"\r\n" + 
	   	    		"input[type=\"hidden\"] {\r\n" + 
	   	    		"    display: none;\r\n" + 
	   	    		"}\r\n" + 
	   	    		"</style>");
	        out.println("<h1>Erreur de saisie </h1>");
	        out.println("<p>Cette carte n'existe pas</p>");
	        out.println("</body></html>");
			return false;
			
		}
	    
    	
    }
    
    private void envoiMailPaiement(Users users) {
        final String username = "projetjee15@gmail.com"; 
        final String password = "tusq mhzz tjws knui";
        System.out.println(users.getEmail());
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(users.getEmail()));
            message.setSubject("Confirmation de paiement");
            message.setText("Cher " + users.getUsername() + ",\n\n"
                    + "Merci de votre achat, en esperant vous revoir bientot !");

            Transport.send(message);

            System.out.println("E-mail de paiement envoyÃ© avec succÃ¨s via Gmail.");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'envoi de l'e-mail de paiement.");
        }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idUsername= Integer.valueOf(request.getParameter("userId"));

		if (Validation(request, response,idUsername)) {
			
			
		ArrayList<bean.Product> panierPaye = SQL.retournerProduitsDuPaniers(idUsername);
		System.out.println("panier payé : "+panierPaye);
        SQL.AjouterPanierCommande(idUsername,panierPaye);
        
		double prixTotal= Double.valueOf(request.getParameter("prixTotal"));
		double pt_fidelite= Double.valueOf(request.getParameter("pt_fidelite"));
		System.out.println(idUsername);
        SQL.updateQuantity(idUsername);
		SQL.supprimerPtFidelite(idUsername, pt_fidelite);
		PanierDao panierdao = new PanierDao();
		for (int i = 0; i < 100; i++) {
			panierdao.RemoveProduct(i, idUsername);
		}
		double pt_fidelite_ajout = (10.0/100)*prixTotal;
		System.out.println("pt fidelite ajout ="+ pt_fidelite_ajout);
		SQL.ajouterPtFidelite(idUsername, pt_fidelite_ajout);
		UserDao userdao = new UserDao();
        envoiMailPaiement(userdao.InfoUser(idUsername));
		}
	}

}
