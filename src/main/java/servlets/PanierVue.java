package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PanierDao;
import DAO.UserDao;
import bean.SQL;
import entity.Paniers;
import entity.Products;

/**
 * Servlet implementation class PanierVue
 */
@WebServlet("/PanierVue")
public class PanierVue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierVue() {
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
		String username= String.valueOf(request.getAttribute("username"));
		if (username.equals("null")) {
			System.out.println("yes");
			username = request.getParameter("username");
		}
		
		long idUsername=userdao.rechercheIdByUsername(username);
		
		PanierDao panierdao = new PanierDao();
		
		//ArrayList<bean.Product> panier = SQL.retournerProduitsDuPaniers(idUsername);
		ArrayList<Products> panier = (ArrayList<Products>) panierdao.retournerProduitsDuPaniers(idUsername);//contient tout les infos du produit
		ArrayList<Paniers> panier2 = (ArrayList<Paniers>) panierdao.retournerProduitsDuPaniers2(idUsername);//contient tout les quantités des produits

		System.out.println("k"+panier);
		int taille =panier.size();
		
		
		request.setAttribute("panier", panier);
		request.setAttribute("panier2", panier2);
		request.setAttribute("taille", taille);


		//request.setAttribute("panier", panier1);

		//double prixTotal=SQL.calculPrixPanier(panier);
		double prixTotal =panierdao.calculPrixPanier(panier,panier2);
		request.setAttribute("prixTotal", prixTotal);
		//request.setAttribute("prixTotal", prixTotal1);

		request.setAttribute("idUsername", idUsername);
		//request.setAttribute(username, "username");
		this.getServletContext().getRequestDispatcher("/WEB-INF/PanierVue.jsp").forward(request, response);

		doGet(request, response);
	}

}
