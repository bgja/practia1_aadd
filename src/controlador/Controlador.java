package controlador;

import java.util.Date;
import java.util.List;

import dao.DAOException;
import dao.DAOFactoria;
import dao.PartidoDAO;
import dao.UsuarioDAO;
import model.Partido;
import model.Usuario;

public class Controlador {
	private static Controlador unicaInstancia;


	private Controlador(){
		try {
			DAOFactoria.setDAOFActoria(DAOFactoria.JPA);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public static Controlador getUnicaInstancia(){
		if(unicaInstancia == null){
			new Controlador();
		}
		return unicaInstancia;
	}

	public Usuario registrarUsuario(String usuario, String clave, String mail, String telefono){
		try{
			UsuarioDAO usuDAO = DAOFactoria.getUnicaInstancia().getUsuarioDAO();
			return usuDAO.createUsuario(usuario, clave, mail, telefono);
		} catch(DAOException e){
			e.printStackTrace();
		}
		return null;
		
	}
	
	public boolean login(String usuario, String clave){
		Usuario user = obtenerUsuario(usuario);
		return user.getContrasena().equals(clave);
	}
	
	public Usuario obtenerUsuario(String usuario){
		try{
			UsuarioDAO usuDAO = DAOFactoria.getUnicaInstancia().getUsuarioDAO();
			Usuario user = usuDAO.findUsuarioByUsuario(usuario);
			return user;
		} catch(DAOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Partido> obtenerPartidoEntreFechas(Date f1, Date f2){
		PartidoDAO parDAO = DAOFactoria.getUnicaInstancia().getPartidoDAO();
		try {
			return parDAO.findPartidoByFecha(f1, f2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
