package application;

import java.time.LocalDate;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Department dep = new Department(1,"Books");
		Seller sel = new Seller(1,"Maria Brown","mariabrown@gmail.com",LocalDate.now(),213.32,dep);
		
		System.out.println(sel);

	}

}
