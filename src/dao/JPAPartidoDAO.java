package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.Partido;

public class JPAPartidoDAO implements PartidoDAO {

	private EntityManagerFactory emf;
	
	public JPAPartidoDAO(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public List<Partido> findPartidoByFecha(Date f1, Date f2) throws DAOException {
			
		EntityManager em = null;
		
		synchronized (emf) {
			em = emf.createEntityManager();
		}
		
		//String jpql = "SELECT p FROM Partido p WHERE p.fecha >= :f1" +"AND p.fecha <= :f2";
		
		//Query query = em.createQuery(jpql);
		Query query = em.createNamedQuery("find");
		query.setParameter("f1", f1);
		query.setParameter("f2", f2);
		return query.getResultList();
	}

	@Override
	public List<Partido> findPartidoByFechaCRIT(Date f1, Date f2) throws DAOException {
			
		EntityManager em = null;
		
		synchronized (emf) {
			em = emf.createEntityManager();
		}
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Partido> criteria = builder.createQuery(Partido.class);
		Root<Partido> p = criteria.from(Partido.class);
		criteria.select(p).where(builder.between(p.get("fecha"), f1, f2));
		Query query = em.createQuery(criteria);
		return query.getResultList();
	}

}
