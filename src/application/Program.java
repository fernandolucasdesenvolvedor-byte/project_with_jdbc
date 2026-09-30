package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.impl.SellerDAOJDBC;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAOJDBC jdbcSeller = (SellerDAOJDBC) DAOFactory.createSellerDAO();
		jdbcSeller.deleteById(9);;
		
		
		List<Seller> sellers = jdbcSeller.findAll();
		sellers.forEach(System.out::println);

	}

}
