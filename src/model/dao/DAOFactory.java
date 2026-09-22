package model.dao;

import model.dao.impl.DepartmentDAOJDBC;
import model.dao.impl.SellerDAOJDBC;

public class DAOFactory {
	
	public static SellerDAO createSellerDAO() {
		return new SellerDAOJDBC();
	}
	
	public static DepartmentDAO createDepartmentDAO() {
		return new DepartmentDAOJDBC();
	}

}
