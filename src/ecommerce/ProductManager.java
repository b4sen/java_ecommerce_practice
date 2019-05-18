package ecommerce;

import java.util.UUID;

public class ProductManager {
	
	private static ProductManager instance = new ProductManager();
	
	// empty constructor to be able to call the methods of this class
	private ProductManager() {
		
	}
	
	// this method returns the ProductManager object
	public static ProductManager getInstance() {
		return instance;
	}
	
	public ProductEntity createProduct(String productName, double productPrice) {
		
		ProductEntity product = new ProductEntity();
		String uid = UUID.randomUUID().toString();
		product.setProductId(uid);
		product.setProductName(productName);
		product.setProductPrice(productPrice);
		
		return product;
	}
	
}
