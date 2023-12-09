package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.PanierDao;
import DAO.UserDao;
import classe.SQL;
import entity.Users;

/**
 * Servlet implementation class removeProduct
 */
@WebServlet("/removeProduct")
public class removeProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeProduct() {
        super();
        // TODO Auto-generated constructor stub
    }
    String username;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

	
		
	

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao userdao = new UserDao();
		PanierDao panierdao = new PanierDao();
		
		
		int idproduit=Integer.parseInt(request.getParameter("productId"));
		int iduser=Integer.parseInt(request.getParameter("userId"));
		System.out.println("id produit :"+idproduit);
		System.out.println("id user :"+iduser);
		
		panierdao.RemoveProduct(idproduit,iduser);
		
		
		
		
		
		Users UserTemp= userdao.InfoUser(iduser);
	
		request.setAttribute("username", UserTemp.getUsername());
		
	
		
		request.getRequestDispatcher("PanierVue").forward(request, response);
	}

}
