package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDAO {

public void insert(Seller seller);
	
	public void update(Seller seller);
	
	public void deleteById(Long id);
	
	public Seller findById(Long id);
	
	public List<Seller> findAll();
	
}
