package application;

import java.time.LocalDate;
import java.util.List;

import model.dao.DAOFactory;
import model.dao.impl.SellerDAOJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAOJDBC jdbcSeller = (SellerDAOJDBC) DAOFactory.createSellerDAO();
		Seller s1 = new Seller(9,"Elvis Presley","ep@gmail.com",LocalDate.parse("1945-01-24"),12000.00,new Department(3,null));
		jdbcSeller.update(s1);
		
		
		List<Seller> sellers = jdbcSeller.findAll();
		sellers.forEach(System.out::println);

	}

}
