package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDao;
import entity.Products;

@WebServlet("/Accueil")
public class Accueil extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupérer la liste des produits
		
		ProductDao productdao = new ProductDao();
		
		
		ArrayList<Products> products = productdao.rechercheproduit("");

		// Passer les données à la JSP

		request.setAttribute("products", products);

		// Rediriger vers la JSP d'accueil
		request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
	}
}