package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PanierDao;
import DAO.UserDao;
import entity.Users;

/**
 * Servlet implementation class ViderPanier
 */
@WebServlet("/ViderPanier")
public class ViderPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViderPanier() {
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
		PanierDao panierdao = new PanierDao();
		
		
		int iduser=Integer.parseInt(request.getParameter("userId"));
		
		System.out.println("id user :"+iduser);
		
		for (int i = 0; i < 100; i++) {
			panierdao.RemoveProduct(i, iduser);
		}
		
		UserDao userdao = new UserDao();
		Users UserTemp= userdao.InfoUser(iduser);
	
		request.setAttribute("username", UserTemp.getUsername());
		
	
		
		request.getRequestDispatcher("PanierVue").forward(request, response);
	}

}
