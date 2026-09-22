package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.impl.SellerDAOJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAOJDBC jdbcSeller = (SellerDAOJDBC) DAOFactory.createSellerDAO();
		List<Seller> sellers = jdbcSeller.findByDepartment(new Department(1,"Foda-se"));
		
		sellers.forEach(System.out::println);

	}

}
