package dao;

import java.util.Date;
import java.util.List;

import model.Partido;

public interface PartidoDAO {

	
	List<Partido> findPartidoByFecha(Date f1, Date f2) throws DAOException;

	List<Partido> findPartidoByFechaCRIT(Date f1, Date f2) throws DAOException;
	
	
}
