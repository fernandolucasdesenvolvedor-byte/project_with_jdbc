package application;

import java.util.List;

import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.dao.impl.DepartmentDAOJDBC;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		
		DepartmentDAO jdbcDepartment = (DepartmentDAOJDBC) DAOFactory.createDepartmentDAO();
		
		
		//jdbcDepartment.insert(new Department(null,"Games"));
		jdbcDepartment.update(new Department(6,"Video-Games"));
		
		
		
		List<Department> departments = jdbcDepartment.findAll();
		departments.forEach(System.out::println);

	}

}
