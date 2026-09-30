package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDAOJDBC implements SellerDAO{
	
	private Connection conn;
	
	public SellerDAOJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, seller.getName());
			ps.setString(2, seller.getEmail());
			ps.setDate(3, java.sql.Date.valueOf(seller.getBirthDate()));
			ps.setDouble(4, seller.getBaseSalary());
			ps.setInt(5, seller.getDepartment().getId());
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			}else {
				throw new DbException("Unexpected Error! No rows affected!");
			}
			
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		try {
			stat = conn.prepareStatement("SELECT seller.*, department.name AS DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.id WHERE seller.id = ?");
			stat.setInt(1, id);
			rs = stat.executeQuery();
			if(rs.next()) {
				Department dept = new Department(rs.getInt("DepartmentId"),rs.getString("DepName"));
				LocalDate date = LocalDate.parse(rs.getDate("BirthDate").toString());
				
				Seller seller = new Seller(rs.getInt("Id"),rs.getString("Name"),rs.getString("Email"),date,rs.getDouble("BaseSalary"),dept);
				return seller;
			}
			
			return null;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(stat);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findAll() {

		Statement st = null;
		ResultSet rs = null;
		
		try {
			List<Seller> sellers = new ArrayList<>();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT seller.*, department.Name AS DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.id ORDER BY seller.Name");
			Map<Integer,Department> departments = new HashMap<>();
			
			while(rs.next()) {
				Department dept = departments.get(rs.getInt("Id"));
				
				if(dept == null) {
					dept = new Department(rs.getInt("DepartmentId"),rs.getString("DepName"));
					departments.put(rs.getInt("Id"), dept);
				}
				
				LocalDate date = LocalDate.parse(rs.getDate("BirthDate").toString());
				
				Seller seller = new Seller(rs.getInt("Id"),rs.getString("Name"),rs.getString("Email"),date,rs.getDouble("BaseSalary"),dept);
				
				sellers.add(seller);
			}
			
			return sellers;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement("SELECT seller.*, department.Name AS DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE department.Id = ? ORDER BY seller.Name",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			List<Seller> sellers = new ArrayList<>();
			rs.next();
			Department dept = new Department(rs.getInt("DepartmentId"), rs.getString("DepName"));
			rs.beforeFirst();
			
			while(rs.next()) {
				LocalDate date = LocalDate.parse(rs.getDate("BirthDate").toString());
				Seller seller = new Seller(rs.getInt("Id"),rs.getString("Name"),rs.getString("Email"),date,rs.getDouble("BaseSalary"),dept);
				sellers.add(seller);
			}
			
			return sellers;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}

}
