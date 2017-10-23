package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import controlador.Controlador;
import model.Usuario;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet(urlPatterns = { "/ServletRegistro" }, initParams = { @WebInitParam(name = "Autor", value = "Antonio") })
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletRegistro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		String telefono = request.getParameter("telefono");
		String mail = request.getParameter("email");
		String contrasena = request.getParameter("password1");
		String contrasena2 = request.getParameter("password2");
		
		if(!contrasena.equals(contrasena2)) response.sendError(response.SC_UNAUTHORIZED, "Contraseñas diferentes.");
		
		Usuario usu = Controlador.getUnicaInstancia().obtenerUsuario(usuario);
		
		if(usu == null){
			response.sendError(response.SC_UNAUTHORIZED, "El usuario ya existe");
		}
		
		usu = Controlador.getUnicaInstancia().registrarUsuario(usuario, contrasena, mail, telefono);
		
		
				
		
		/*
		ServletContext contexto = getServletConfig().getServletContext();
		if(contexto.getAttribute("mapUsuarios")== null){
			contexto.setAttribute("mapUsuarios", new HashMap<String, Usuario>());
		}
		
		HashMap<String, Usuario> mapUsuarios = (HashMap<String, Usuario>)contexto.getAttribute("mapUsuarios");*/
		HttpSession session = request.getSession();
		session.setAttribute("usuarioActual", Controlador.getUnicaInstancia().obtenerUsuario(usuario));
		
		
		response.getWriter().append("<html>")
							.append("<head><title>Usuario registrado</title></head>")
							.append("<body> Se ha registrado el usuario:" + usu.getUsuario() + "<br/>")
							.append("--> autor:" + getServletConfig().getInitParameter("Autor"))
							.append("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
