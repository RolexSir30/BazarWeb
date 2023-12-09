package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDao;
import bean.SQL;

/**
 * Servlet implementation class SupprimerUser
 */
@WebServlet("/SupprimerUser")
public class SupprimerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerUser() {
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
		String username=request.getParameter("username");
		System.out.println("supprimer : "+username);
		
		UserDao userdao = new UserDao();
		userdao.deleteUserByUsername(username);
		
		request.setAttribute("username", username)	;
		
		ArrayList<String> listeClients = userdao.clients();
		System.out.println("liste clients"+listeClients);

		request.setAttribute("clients", listeClients);
		
		ArrayList<String> listeModerateur = userdao.moderateurs();
		System.out.println("modos"+listeModerateur);
		request.setAttribute("modo", listeModerateur);
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/adminconnected.jsp").forward(request, response);

	
	}

}
