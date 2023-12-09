package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDao;
import classe.SQL;
import entity.Users;

/**
 * Servlet implementation class HistoriqueCommande
 */
@WebServlet("/HistoriqueCommande")
public class HistoriqueCommande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoriqueCommande() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userId=Integer.parseInt(request.getParameter("userId"));
		request.setAttribute("userId",userId);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/HistoriqueCommandeFormulaire.jsp").forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int userId=Integer.parseInt(request.getParameter("userId"));
		String password = request.getParameter("password");
		UserDao userdao = new UserDao();
		
		if(userdao.connectionClient(userId,password)) {
	
			
		ArrayList<classe.Commande> commandes = SQL.retournerProduitsCommandes(userId);
		request.setAttribute("commandes", commandes);
		System.out.println(commandes);
		Users usertemp = userdao.InfoUser(userId);
		request.setAttribute("username",usertemp.getUsername());
		request.setAttribute("password", usertemp.getPassword());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/CommandeVue.jsp").forward(request, response);
		
		
		
		}

	}

}
