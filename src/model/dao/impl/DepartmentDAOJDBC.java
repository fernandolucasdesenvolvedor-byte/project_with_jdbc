package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Department;

public class DepartmentDAOJDBC implements DepartmentDAO{
	
	private Connection conn;
	
	public DepartmentDAOJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			stat = conn.prepareStatement("SELECT * FROM department WHERE id = ?");
			stat.setInt(1, id);
			rs = stat.executeQuery();

			if(rs.next()) {
				Department dept = new Department(rs.getInt("Id"),rs.getString("Name"));
				return dept;
			}
			
			
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally{
			DB.closeStatement(stat);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
