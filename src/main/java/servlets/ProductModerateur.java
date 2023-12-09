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
import bean.SQL;
import entity.Products;

/**
 * Servlet implementation class ProductModerateur
 */
@WebServlet("/ProductModerateur")
public class ProductModerateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductModerateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductDao productdao = new ProductDao();
		
		
		HttpSession session = request.getSession();
		
        String username=request.getParameter("username");
		//ArrayList<bean.Product> po=new ArrayList<>();
		
		ArrayList<Products> po = new ArrayList<>();
		
		int i=0;
        request.setAttribute("compteur", i);
			i++;
			String productName=request.getParameter("recherche");
			session.setAttribute("username", username);
			po=productdao.rechercheproduitParNomEtModo(productName,username);
			//po=SQL.rechercheproduitModerateur(productName,username);//permet de chercher les produits dans la bdd et retourner dans une liste.
            session.setAttribute("products", po);//envoie le param�tre vers la JSP
            session.setAttribute("compteur", i);//renvoie le compteur vers la JSP
    		this.getServletContext().getRequestDispatcher("/WEB-INF/ModerateurConnected.jsp").forward(request, response);

		}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			ProductDao productdao = new ProductDao();
		
		    String productName = request.getParameter("name");
	        String productDescription = request.getParameter("description");
	        double productPrice = Double.parseDouble(request.getParameter("price"));
	        int stockQuantity = Integer.parseInt(request.getParameter("stock_quantity"));
	        String categoryId = request.getParameter("category_id");
	        String imageLink = request.getParameter("image");
	        String usernameModo = request.getParameter("username");
	        
	       // bean.Product producto = new bean.Product(productName,productDescription,productPrice,stockQuantity,categoryId,imageLink,usernameModo);
	        Products producto = new Products(productName,productDescription,productPrice,stockQuantity,categoryId,imageLink,usernameModo);
	        
	        productdao.ajouterProduit(producto);
	        //SQL.ajouterproduitModo(producto);
	        
	        
	        PrintWriter out = response.getWriter();

	        out.println("<html><body style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #2ecc71; color: #fff; text-align: center; margin: 0; padding: 0; display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh;\">");
	        out.println("<h1 style=\"font-size: 36px; color: #fff; margin-bottom: 10px;\">Ajout Réussi</h1>");
	        out.println("<p style=\"font-size: 18px; margin: 0; margin-bottom: 20px;\">Votre produit a été ajouté avec succès!</p>");
	        out.println("<a href=\"Moderateur\" style=\"color: #fff; text-decoration: none; font-weight: bold; border: 2px solid #fff; padding: 10px 20px; border-radius: 5px; transition: background-color 0.3s, color 0.3s;\" onmouseover=\"this.style.backgroundColor='#fff'; this.style.color='#2ecc71';\" onmouseout=\"this.style.backgroundColor='#2ecc71'; this.style.color='#fff';\">D&eacute;connexion</a>");
	        out.println("</body></html>");
	        
	        
	        
	}

}
