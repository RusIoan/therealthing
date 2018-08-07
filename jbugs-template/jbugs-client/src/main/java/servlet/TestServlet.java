package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.msg.ro.boundary.UserManagement;
import edu.msg.ro.exceptions.BusinessException;
import edu.msg.ro.persistence.user.entity.User;
import edu.msg.ro.transfer.UserDTO;

@WebServlet(urlPatterns = { "/TestServlet" })
public class TestServlet extends HttpServlet {




	@EJB
	private UserManagement userManagement;
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {


		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName("dorel");
		userDTO.setLastName("dorel");
		userDTO.setEmail("doreldorel@msggroup.com");
		userDTO.setPassword("Password");
		userDTO.setPhoneNumber("1234567890");



		//UserDTO user2DTO = userDTO;
		//user2DTO.setFirstName("asdf");
		UserDTO persistentUserDTO = null;
		try {
			persistentUserDTO = userManagement.createUser(userDTO);
			//userManagement.deactivateUser("doreld");

		}
		catch (BusinessException e){
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println(persistentUserDTO.toString());
		}


		//permissionManagement.addPermission(p1);
		//permissionManagement.addPermission(p2);


		//permissionManagement.addPermission(p1);
		//permissionManagement.createPermissionForRole(r1,p1);
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on
	// the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}


/*
@Singleton
@Startup
class TestingClass{

	@PostConstruct
	public void printBeforeTime(){
		System.out.println("BEFORE:: " + java.time.LocalTime.now());
	}

	@PreDestroy
	public void printAfterTime(){
		System.out.println("AFTER:: " + java.time.LocalTime.now());
	}


}
*/