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
import bean.SQL;
import entity.Products;

/**
 * Servlet implementation class Product
 */
@WebServlet("/Product")
public class Product extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Product() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//La m�thode do get a pour utilit� de rechercher les produits par l'admin.
		
		HttpSession session = request.getSession();
		Object admin = session.getAttribute("admin");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ProductDao productdao = new ProductDao();
		ArrayList<Products> po=new ArrayList<>();
		int i=0;
        request.setAttribute("compteur", i);
        UserDao userdao =new UserDao();
		if(userdao.connectionAdmin(username,password)) {
			i++;
			String productName=request.getParameter("recherche");
			po=productdao.rechercheproduit(productName);//permet de chercher les produits dans la bdd et retourner dans une liste.
            request.setAttribute("products", po);//envoie le param�tre vers la JSP
            request.setAttribute("compteur", i);//renvoie le compteur vers la JSP
    		this.getServletContext().getRequestDispatcher("/WEB-INF/adminconnected.jsp").forward(request, response);

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//on r�cupere la variable admin de la session
		HttpSession session = request.getSession();
		Object admin = session.getAttribute("admin");
		
		
		//if(SQL.connectionAdmin((User) admin)) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String price1 =  request.getParameter("price");
        String stock_quantiti = request.getParameter("stock_quantity");
        int stock_quantity=Integer.parseInt(stock_quantiti);
        double price =Double.parseDouble(price1);
        String category_id = request.getParameter("category_id");
        String image = request.getParameter("image");
        
        bean.Product producto = new bean.Product(name,description,price,stock_quantity,category_id,image);
        
        
        ProductDao productdao = new ProductDao();
        Products product = new Products(name, description, price, stock_quantity, category_id, image, "admin");
        
        productdao.ajouterProduit(product);
        
        
        PrintWriter out = response.getWriter();

        out.println("<html><body style=\"font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #2ecc71; color: #fff; text-align: center; margin: 0; padding: 0; display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100vh;\">");
        out.println("<h1 style=\"font-size: 36px; color: #fff; margin-bottom: 10px;\">Ajout Réussi</h1>");
        out.println("<p style=\"font-size: 18px; margin: 0; margin-bottom: 20px;\">Votre produit a été ajouté avec succès!</p>");
        out.println("<a href=\"Administrateur\" style=\"color: #fff; text-decoration: none; font-weight: bold; border: 2px solid #fff; padding: 10px 20px; border-radius: 5px; transition: background-color 0.3s, color 0.3s;\" onmouseover=\"this.style.backgroundColor='#fff'; this.style.color='#2ecc71';\" onmouseout=\"this.style.backgroundColor='#2ecc71'; this.style.color='#fff';\">D&eacute;connexion</a>");
        out.println("</body></html>");
        
        
        
        
		}
		
		
		//}
        
	
	
	

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
