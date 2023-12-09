package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDao;
import bean.SQL;

/**
 * Servlet implementation class Paiement
 */
@WebServlet("/Paiement")
public class Paiement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paiement() {
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
		UserDao userdao = new UserDao();
		// Retrieve the user ID and panier ArrayList from request attributes
	    int userId = Integer.parseInt(request.getParameter("idUser"));
	    double prixTotal=Double.parseDouble(request.getParameter("prixTotal"));

	    request.setAttribute("userId", userId);
	    request.setAttribute("prixTotal", prixTotal);
	    request.setAttribute("username", userdao.RechercherNomFromId((long)(userId)));
	   // System.out.println(userdao.RechercherNomFromId((long)(userId)));
        this.getServletContext().getRequestDispatcher("/WEB-INF/Paiement.jsp").forward(request, response);


}}
