package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.PanierDao;
import DAO.UserDao;
import bean.SQL;
import entity.Paniers;

/**
 * Servlet implementation class Panier
 */
@WebServlet("/Panier")
public class Panier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Panier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/PanierVue.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserDao userdao = new UserDao();
		PanierDao panierdao = new PanierDao();
		
		
		String productI = request.getParameter("productId");
		int productId=Integer.parseInt(productI);
        String usernameClient = request.getParameter("usernameClient");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String password=request.getParameter("password");

        // You can now use these values as needed in your servlet logic
        // For example, you can print them to the console or use them in your business logic
        HttpSession session = request.getSession();

        // Store the information in the session
        session.setAttribute("usernameClient", usernameClient);
        session.setAttribute("password", password);
        
        System.out.println("Product ID: " + productId);
        System.out.println("Username Client: " + usernameClient);
        System.out.println("Quantity: " + quantity);

        if(userdao.connectionClient(usernameClient,password)) {
       	 PrintWriter out = response.getWriter();
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
	        out.println("<h1>Le produit a &eacute;t&eacute; ajout&eacute; au panier </h1>");
	        out.println("<p>Voir mon panier :</p>"
	        	);
	        
	        
	        
	        out.println("</body></html>");
	        out.println("<form method='post' action='PanierVue'>");
	        out.println("<input type='hidden' name='username' value='" + usernameClient + "'>");

	        out.println("<input type='submit' value='Voir mon panier'>");
	        out.println("</form>");

	        out.println("<form method='post' action='Client'>");
	        out.println("<input type='hidden' name='username' value="+usernameClient+">");
	        out.println("<input type='hidden' name='password' value="+password+">");
	        out.println("<input type='submit' value='Retourner &agrave; la page des produits'>");
	        out.println("</form>");
        	SQL.ajouterPanier(productId,userdao.rechercheIdByUsername(usernameClient),quantity);
        	//Paniers panier =new Paniers((long)productId,(long) SQL.RechercheUserId(usernameClient),quantity);
        	//panierdao.addPanier(new Paniers((long)productId,(long) SQL.RechercheUserId(usernameClient),quantity));

        }else {
	        PrintWriter out = response.getWriter();
	        
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
	        out.println("<h1>Erreur de connection</h1>");
	        out.println("<p>le mot de passe saisie est faux</p>");
	        out.println("</body></html>");
        }
        
        
        
        
        
        
        
        

		
		
		
	}

}
