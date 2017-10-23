package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import model.Usuario;

public class JPAUsuarioDAO implements UsuarioDAO {

	private EntityManagerFactory emf;
	
	public JPAUsuarioDAO(EntityManagerFactory emf){
		this.emf = emf;
	}
	
	@Override
	public Usuario createUsuario(String usuario, String clave, String mail, String telefono) throws DAOException {
		EntityManager em = null;
		
		synchronized (emf) {
			em = this.emf.createEntityManager();
		}
		
		
		Usuario usu = new Usuario();
		usu.setUsuario(usuario);
		usu.setContrasena(clave);
		usu.setMail(mail);
		usu.setTelefono(telefono);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		em.persist(usu);
		tx.commit();
		em.close();
		
		return usu;
	}

	@Override
	public Usuario findUsuarioByUsuario(String usuario) throws DAOException {
		EntityManager em = null;
		
		synchronized (emf) {
			em = emf.createEntityManager();
		}
		
		Usuario usu = em.find(Usuario.class, usuario);
		
		em.close();
		
		return usu;
	}

}
