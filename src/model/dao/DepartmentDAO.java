package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDAO {

	public void insert(Department department);
	
	public void update(Department department);
	
	public void deleteById(Long id);
	
	public Department findById(Long id);
	
	public List<Department> findAll();
	
}
