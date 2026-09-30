package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.dao.impl.DepartmentDAOJDBC;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		DepartmentDAO jdbcDepartment = (DepartmentDAOJDBC) DAOFactory.createDepartmentDAO();
		
		jdbcDepartment.deleteById(5);
		
		
		
		List<Department> departments = jdbcDepartment.findAll();
		departments.forEach(System.out::println);

	}

}
