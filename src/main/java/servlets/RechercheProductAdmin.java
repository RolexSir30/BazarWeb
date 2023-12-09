package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ProductDao;
import DAO.UserDao;
import classe.SQL;
import entity.Products;

/**
 * Servlet implementation class RechercheProductAdmin
 */
@WebServlet("/RechercheProductAdmin")
public class RechercheProductAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheProductAdmin() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		
		ProductDao productdao = new ProductDao();
		UserDao userdao = new UserDao();
		HttpSession session = request.getSession();
		Object admin = session.getAttribute("admin");
		//ArrayList<bean.Product> po=new ArrayList<>();
		
		ArrayList<Products> po =new ArrayList<>();
		
		int i=0;
        request.setAttribute("compteur", i);

		//if(SQL.connectionAdmin((User) admin)) {
			i++;
			
			ArrayList<String> listeClients = userdao.clients();

			request.setAttribute("clients", listeClients);
			
			ArrayList<String> listeModerateur = userdao.moderateurs();
			request.setAttribute("modo", listeModerateur);
			
			String productName=request.getParameter("recherche");
			po=productdao.rechercheproduit(productName);//permet de chercher les produits dans la bdd et retourner dans une liste.
            request.setAttribute("products", po);//envoie le param�tre vers la JSP
            request.setAttribute("compteur", i);//renvoie le compteur vers la JSP
    		this.getServletContext().getRequestDispatcher("/WEB-INF/adminconnected.jsp").forward(request, response);

		//}
	}

}
