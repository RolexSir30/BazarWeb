 package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDao;
import DAO.UserDao;
import entity.Products;
import entity.Users;

/**
 * Servlet implementation class Administrateur
 */
@WebServlet("/Administrateur")
public class Administrateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Administrateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean connection =true;
		request.setAttribute("connection",connection);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean connection =true;
		request.setAttribute("connection",connection);
		String username = request.getParameter("username");		
		String password = request.getParameter("password");		
		Users admin = new Users(username,password);
		int i=1;
		UserDao userdao = new UserDao();
		ProductDao productdao = new ProductDao();
		
		if(userdao.connectionAdmin(username, password)) {
			
			//On va mettre la variable admin dans session
	        HttpSession session = request.getSession();
	        //session.setAttribute("user", admin);
	        session.setAttribute("admin", admin);
			ArrayList<String> listeClients = userdao.clients();
			System.out.println("liste clients"+listeClients);

			request.setAttribute("clients", listeClients);
			
			ArrayList<String> listeModerateur = userdao.moderateurs();
			System.out.println("modos"+listeModerateur);
			request.setAttribute("modo", listeModerateur);
			
			
			ArrayList<Products> po =new ArrayList<>();
			po=productdao.rechercheproduit("");
            request.setAttribute("products", po);//envoie le paramï¿½tre vers la JSP

			
			this.getServletContext().getRequestDispatcher("/WEB-INF/adminconnected.jsp").forward(request, response);
			


		
			
		}else {
	        PrintWriter out = response.getWriter();

	        out.println("<html><body>");
	        out.println("<h1>Erreur de connection</h1>");
	        out.println("<p>Les champs saisies sont faux</p>");
	        out.println("</body></html>");
		}

        
        
        

		//if l'admin c celui de la bdd alors on met connection a fausse qu'on va send a la jsp ensuite faire un truc jstl e faire un si pour accéder a la page
		

	}

}
