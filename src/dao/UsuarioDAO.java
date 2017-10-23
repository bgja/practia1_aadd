package dao;

import model.Usuario;

public interface UsuarioDAO {

	public Usuario createUsuario(String usuario, String clave, String mail, String telefono) throws DAOException;

	// Busca un Usuario por "usuario" (clave primaria). Si no lo encuentra
	// devuelve "null"
	public Usuario findUsuarioByUsuario(String usuario) throws DAOException;
}
