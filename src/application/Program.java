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
		List<Seller> sellers = jdbcSeller.findAll();
		Seller s1 = new Seller(null,"Michael Jackson","mj@gmail.com",LocalDate.parse("1973-06-04"),12000.00,new Department(1,"Music"));
		jdbcSeller.insert(s1);
		
		sellers.forEach(System.out::println);

	}

}
