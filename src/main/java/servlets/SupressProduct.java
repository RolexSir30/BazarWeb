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
import bean.SQL;
import entity.Products;

/**
 * Servlet implementation class SupressProduct
 */
@WebServlet("/SupressProduct")
public class SupressProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupressProduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		HttpSession session = request.getSession();
		Object admin = session.getAttribute("admin");
		ArrayList<String> listeClients = userdao.clients();

		request.setAttribute("clients", listeClients);
		ProductDao productdao = new ProductDao();
		ArrayList<String> listeModerateur = userdao.moderateurs();
		request.setAttribute("modo", listeModerateur);
		// if(SQL.connectionAdmin((User) admin)) {

		// V�rifier si le bouton "Supprimer du stock" a �t� cliqu�
		if (request.getParameter("deleteFromStock") != null) {
			int productId = Integer.parseInt(request.getParameter("idproduct"));
			// Mettez en �uvre la logique pour supprimer du stock ici

			productdao.supprimerUnElementDuStock(productId);

		}
		// V�rifier si le bouton "Supprimer de la base de donn�es" a �t� cliqu�
		else if (request.getParameter("deleteFromDB") != null) {
			int productId = Integer.parseInt(request.getParameter("idproduct"));
			// Mettez en �uvre la logique pour supprimer de la base de donn�es ici
			productdao.supprimerProduitDelaBDD(productId);
		}
		// }

		ArrayList<Products> po = new ArrayList<>();
		po = productdao.rechercheproduit("");
		request.setAttribute("products", po);// envoie le param�tre vers la JSP
		this.getServletContext().getRequestDispatcher("/WEB-INF/adminconnected.jsp").forward(request, response);

	}

}
