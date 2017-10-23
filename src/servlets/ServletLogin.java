package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.runtime.reflect.Accessor.GetterSetterReflection;

import controlador.Controlador;
import model.Usuario;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<html><head><title>Login de usuario</title></head>")
							.append("<body><form action='login' method= 'post'>")
							.append("<table>")
							.append("<tr><td>Usuario</td><td><input type='text' name= 'usuario'/>")
							.append("<tr><td>Clave</td><td><input type='password' name= 'clave'/>")
							.append("<tr><td><input type='submit' value='Enviar''/>")
							.append("</table>")
							.append("</form>")
							.append("</body>")
							.append("</html>");
	}

	private void errorUsuario(HttpServletResponse response) throws IOException{
		response.setHeader("refresh", "5;login");
		response.getWriter().append("<html><body>Error en el login.</br>")
				.append("<a href= 'login>Login</a>'</body></html>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("password");
		
		ServletContext context = getServletConfig().getServletContext();
		HashMap<String, Usuario> mapa = (HashMap<String, Usuario>) context.getAttribute("mapausuarios");
		Usuario user = mapa.get(usuario);
		
		/*
		if(context.getAttribute("mapaUsuarios") == null){
			
			
			if(user != null){
				if(user.getContrasena().equals(clave)){
					HttpSession sesion = request.getSession();
					sesion.setAttribute("usuarioActual", user);
					
					RequestDispatcher rd = request.getRequestDispatcher("ServletMain");
					rd.forward(request, response);
					
				}
			}
		}
		*/
		
		if(Controlador.getUnicaInstancia().login(usuario, clave)){
			HttpSession sesion = request.getSession();
			sesion.setAttribute("usuarioActual", Controlador.getUnicaInstancia().obtenerUsuario(usuario));
			
			RequestDispatcher rd = request.getRequestDispatcher("ServletMain");
			rd.forward(request, response);
		} else{
			errorUsuario(response);
		}
		
		
		
		
	}

}
