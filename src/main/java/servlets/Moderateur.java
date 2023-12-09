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
import classe.Product;
import classe.SQL;
import entity.Products;
import entity.Users;

/**
 * Servlet implementation class Moderateur
 */
@WebServlet("/Moderateur")
public class Moderateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Moderateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/Moderateur.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password=request.getParameter("password");
		
		
		Users moderateur = new Users(username,password);
		UserDao userdao = new UserDao();
		ProductDao productdao = new ProductDao();
		
		if(userdao.connectionModo(username, password)) {
	        ArrayList<Products> products = productdao.rechercheproduitModo(username);

	        //System.out.println("moderateur liste pdts : "+products);
	        request.setAttribute("products", products);
	        HttpSession session = request.getSession();
	        session.setAttribute("username", username);
	        session.setAttribute("password", password);
           // request.setAttribute("username", username);
           //  System.out.println(username);
	        session.setAttribute("moderateur", moderateur);
	        
	        
	        
			this.getServletContext().getRequestDispatcher("/WEB-INF/ModerateurConnected.jsp").forward(request, response);
			


		
			
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
