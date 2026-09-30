package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement("INSERT INTO department (Name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,department.getName());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					department.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected Error! No rows affected!");
			}
			
		}catch(SQLException e) {
			
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Department department) {

		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement("UPDATE department SET Name = ? WHERE Id = ?");
			
			ps.setString(1,department.getName());
			ps.setInt(2,department.getId());
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
		
	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement ps = null;
		
		try {
			
			ps = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
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
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM department");
			List<Department> departments = new ArrayList<>();
			
			while(rs.next()) {
				Department department = new Department(rs.getInt("Id"),rs.getString("Name"));
				departments.add(department);
			}
			
			return departments;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
