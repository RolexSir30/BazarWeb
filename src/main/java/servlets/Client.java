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
import bean.Product;
import bean.SQL;
import entity.Products;

@WebServlet("/Client")
public class Client extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Vérifier si l'utilisateur est connecté en tant qu'administrateur
		//HttpSession session = request.getSession();
		//Object admin = session.getAttribute("admin");
        //boolean isAdmin = SQL.connectionAdmin((User)admin);

        // Récupérer la liste des produits
		ProductDao productdao = new ProductDao();
		
        ArrayList<Products> products = productdao.rechercheproduit("");

        // Passer les données à la JSP
        //request.setAttribute("isAdmin", isAdmin);
        request.setAttribute("products", products);

        // Rediriger vers la JSP d'accueil
        request.getRequestDispatcher("/WEB-INF/Client.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String username = request.getParameter("username");		
		String password = request.getParameter("password");		
		int i=1;
		UserDao userdao = new UserDao();
		ProductDao productdao = new ProductDao();
		
		if(userdao.connectionClient(username, password)) {
	        ArrayList<Products> products = productdao.rechercheproduit("");
	        int pt_fidelite=userdao.getPointsDeFidelite(username);
	        
	        Long userId=userdao.rechercheIdByUsername(username);
	        request.setAttribute("products", products);
	        HttpSession session = request.getSession();
	        session.setAttribute("username", username);
	        session.setAttribute("password", password);
	        session.setAttribute("pt_fidelite", pt_fidelite);

	        session.setAttribute("userId",userId);
           // request.setAttribute("username", username);
            System.out.println(username);
			this.getServletContext().getRequestDispatcher("/WEB-INF/ClientConnected.jsp").forward(request, response);
			


		
			
		}else {
	        PrintWriter out = response.getWriter();

	        out.println("<html><head>");
	        out.println("<style>");
	        out.println("    body {");
	        out.println("        font-family: Arial, sans-serif;");
	        out.println("        background-color: #f4f4f4;");
	        out.println("        margin: 0;");
	        out.println("        padding: 0;");
	        out.println("        display: flex;");
	        out.println("        align-items: center;");
	        out.println("        justify-content: center;");
	        out.println("        height: 100vh;");
	        out.println("    }");
	        out.println("");
	        out.println("    .error-container {");
	        out.println("        text-align: center;");
	        out.println("        padding: 20px;");
	        out.println("        background-color: #fff;");
	        out.println("        border-radius: 8px;");
	        out.println("        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
	        out.println("    }");
	        out.println("");
	        out.println("    h1 {");
	        out.println("        color: #ff6347; /* Tomato color */");
	        out.println("    }");
	        out.println("");
	        out.println("    p {");
	        out.println("        color: #333;");
	        out.println("    }");
	        out.println("</style>");
	        out.println("</head><body>");
	        out.println("<div class='error-container'>");
	        out.println("<h1>Erreur de connection</h1>");
	        out.println("<p>Les champs saisis sont faux</p>");
	        out.println("</div>");
	        out.println("</body></html>");

		}

	}
}