package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDao;
import bean.SQL;
import entity.Products;

/**
 * Servlet implementation class InfoProduct
 */
@WebServlet("/InfoProduct")
public class InfoProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//On récupère ici l'id du produit
		int productId =Integer.parseInt(request.getParameter("productId"));
	    request.setAttribute("productId", productId);
		
	    
	    ProductDao productdao = new ProductDao();
	    
	    
		Products product=productdao.getProduct((long)productId);
		
		request.setAttribute("product", product);
		this.getServletContext().getRequestDispatcher("/WEB-INF/InfoProduit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/InfoProduit.jsp").forward(request, response);
	}

}
