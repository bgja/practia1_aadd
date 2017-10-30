package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Controlador;
import model.Usuario;

/**
 * Servlet implementation class ServletsModificar
 */
@WebServlet("/ServletsModificar")
public class ServletsModificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletsModificar() {
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
		String usuario = request.getParameter("usuario");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("email");
		String contrasena = request.getParameter("password1");
		String contrasena2 = request.getParameter("password2");
		
		if(!contrasena.equals(contrasena2)) response.sendError(response.SC_UNAUTHORIZED, "Contraseñas diferentes.");
		
		Usuario usu = Controlador.getUnicaInstancia().modificarUsuario(usuario, contrasena, mail, telefono);
		
		doGet(request, response);
	}

}
