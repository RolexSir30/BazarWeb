package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import DAO.UserDao;
import bean.SQL;
import entity.Users;

/**
 * Servlet implementation class InscriptionModerateur
 */
@WebServlet("/InscriptionModerateur")
public class InscriptionModerateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InscriptionModerateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        this.getServletContext().getRequestDispatcher("/WEB-INF/InscriptionModerateur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  	String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        String address = request.getParameter("address");
	        String postalCode = request.getParameter("postalCode");

	        Users newUser = new Users(username, password, email,"moderateur", address, postalCode,0);

	        UserDao userdao = new UserDao();
	        if(!userdao.UsernameExist(username)) {
		         
	    	    userdao.addUser(newUser);

	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            session.setAttribute("password", password);
	        	
	        request.getRequestDispatcher("/WEB-INF/ModerateurConnected.jsp").forward(request, response);

	     /**   if (SQL.ajouterModerateur(user)) {
	            sendConfirmationEmail(user);

	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            session.setAttribute("password", password);
	           request.getRequestDispatcher("/WEB-INF/ModerateurConnected.jsp").forward(request, response);
	        } else {
	        	PrintWriter out = response.getWriter();

		        out.println("<html><body>");
		        out.println("<h1>Erreur de connection</h1>");
		        out.println("<p>Erreur lors de l'inscription</p>");
		        out.println("</body></html>");
			}**/}else {
				PrintWriter out = response.getWriter();

			    out.println("<html><body>");
			    out.println("<h1>Ce nom d'utilisateur est déjà pris</h1>");
			    out.println("<p>Veuillez choisir un autre</p>");
			    out.println("</body></html>");
			}
	        
	    }

	    private void sendConfirmationEmail(Users user) {
	        final String username = "projetjee15@gmail.com"; 
	        final String password = "tusq mhzz tjws knui";

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
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
	            message.setSubject("Confirmation d'inscription");
	            message.setText("Cher " + user.getUsername() + ",\n\n"
	                    + "Merci de vous être inscrit sur notre site en tant que modérateur. Votre inscription a été confirmée avec succès.");

	            Transport.send(message);

	            System.out.println("E-mail de confirmation envoyé avec succès via Gmail.");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	            System.out.println("Erreur lors de l'envoi de l'e-mail de confirmation.");
	        }
	
	
	
	
	
	}

}
