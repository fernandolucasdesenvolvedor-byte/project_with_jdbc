package application;

import model.dao.DAOFactory;
import model.dao.impl.DepartmentDAOJDBC;
import model.dao.impl.SellerDAOJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		SellerDAOJDBC jdbcSeller = (SellerDAOJDBC) DAOFactory.createSellerDAO();
		Seller sel = jdbcSeller.findById(3);
		DepartmentDAOJDBC jdbcDepartment = (DepartmentDAOJDBC) DAOFactory.createDepartmentDAO();
		Department dep = jdbcDepartment.findById(2);
		
		System.out.println(sel);
		System.out.println(dep);

	}

}
