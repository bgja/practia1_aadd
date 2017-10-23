package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controlador.Controlador;
import model.Alineacion;
import model.Color;
import model.Partido;
import model.Usuario;

/**
 * Servlet implementation class ServletPrueba
 */
@WebServlet("/ServletPrueba")
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPrueba() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//Nombre del persistance.xml <persistence-unit>
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practica1");
		EntityManager em = emf.createEntityManager();
		
		Usuario usu = new Usuario();
		usu.setUsuario("antonio");
		usu.setContrasena("prueba");
		usu.setMail("prueba@prueba.es");
		usu.setTelefono("12345687");
		
		Alineacion ali = new Alineacion();
		ali.setColor(Color.AMARILLO);
		ali.setNombre("tiburones");
		ali.setTanteo(2);
		
		Usuario usu2 = new Usuario();
		usu2.setUsuario("alfonso");
		usu2.setContrasena("prueba");
		usu2.setMail("prueba@prueba.es");
		usu2.setTelefono("12345687");
		
		Alineacion ali2 = new Alineacion();
		ali2.setColor(Color.AMARILLO);
		ali2.setNombre("cobras");
		ali2.setTanteo(2);
		
		Partido par = new Partido();
		par.setFecha(new Date());
		
		usu.getAlineaciones().add(ali);
		usu.getPartidos().add(par);
		usu2.getAlineaciones().add(ali2);
		usu2.getPartidos().add(par);
		
		ali.getUsuarios().add(usu);
		ali.setPartido(par);
		ali2.getUsuarios().add(usu2);
		ali2.setPartido(par);
		
		
		
		par.getAsistentes().add(usu);
		par.getAsistentes().add(usu2);
		
		par.getAlineaciones().add(ali);
		par.getAlineaciones().add(ali2);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(usu);
		em.persist(ali);
		em.persist(usu2);
		em.persist(ali2);
		
		em.persist(par);
		//em.flush();
		
		tx.commit();
		
		em.close();
		
		Date fecha = new Date();
		Date fecha1 = new Date(fecha.getTime() - 60000);
		Date fecha2 = new Date(fecha.getTime() + 60000);
		List<Partido> partidos = Controlador.getUnicaInstancia().obtenerPartidoEntreFechas(fecha1, fecha2);
		
		if(partidos != null){
			for (Partido partido : partidos) {
				response.getWriter().append("Fecha: " + partido.getFecha() + "<br/>");
				for (Alineacion alineacion : partido.getAlineaciones()) {
					response.getWriter().append("Alineacion: " + alineacion.getNombre() + ", color : " + alineacion.getColor() + "<br/>");
					for (Usuario usuario : alineacion.getUsuarios()) {
						response.getWriter().append("Usuario: " + usuario.getUsuario() + "<br/>");
					}
				}
			}
		}
		
		
		emf.close();
		
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
