package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDao;
import DAO.UserDao;
import classe.SQL;
import entity.Products;

/**
 * Servlet implementation class SupressProductModerateur
 */
@WebServlet("/SupressProductModerateur")
public class SupressProductModerateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupressProductModerateur() {
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
		UserDao userdao = new UserDao();
		ProductDao productdao = new ProductDao();
		ArrayList<String> listeClients = userdao.clients();
		String username = request.getParameter("username");

		
		

        //System.out.println("moderateur liste pdts : "+products);
        
		request.setAttribute("clients", listeClients);
		
		ArrayList<String> listeModerateur = userdao.moderateurs();
		request.setAttribute("modo", listeModerateur);
		request.setAttribute("username", username);
        if (request.getParameter("deleteFromStock") != null) {
            int productId = Integer.parseInt(request.getParameter("idproduct"));
           //  SQL.supprimerDuStock(productId);
            productdao.supprimerUnElementDuStock(productId);
            ArrayList<Products> products = productdao.rechercheproduitModo(username);
            request.setAttribute("products", products);

        }
        else if (request.getParameter("deleteFromDB") != null) {
            int productId = Integer.parseInt(request.getParameter("idproduct"));
           // SQL.supprimerDeLaBDD(productId);
            productdao.supprimerProduitDelaBDD(productId);
            ArrayList<Products> products = productdao.rechercheproduitModo(username);
            request.setAttribute("products", products);

        }

		this.getServletContext().getRequestDispatcher("/WEB-INF/ModerateurConnected.jsp").forward(request, response);
	}

}
