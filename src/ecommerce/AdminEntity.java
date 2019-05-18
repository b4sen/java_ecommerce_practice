package ecommerce;

import java.util.Scanner;
import java.util.UUID;

public class AdminEntity extends UserEntity {
	static Scanner scan = new Scanner(System.in);
	
	
	public static ProductEntity createProduct() {
		String productName = null;
		double productPrice = 0;
		String productId = UUID.randomUUID().toString();
		
		ProductEntity product = new ProductEntity();
		

		System.out.print("Product name:");
		productName = scan.nextLine();
		System.out.print("Product price:");
		productPrice = scan.nextDouble();
		
		
		product.setProductId(productId);
		product.setProductName(productName);
		product.setProductPrice(productPrice);


		
		return product;
	}

	public static String removeProduct() {
		String productName = null;
		System.out.print("Product name to remove: ");
		productName = scan.nextLine();
		System.out.println(productName + " will be removed");
		return productName;
		
	}

	
	
}
