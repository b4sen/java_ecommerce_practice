package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
	private static List<UserEntity> userList = new ArrayList<UserEntity>();
	private static List<ProductEntity> productList = new ArrayList<ProductEntity>();
	
	public static void loadData() {
		loadUser();
		loadProduct();
	}
	

	
	private static void loadUser() {
		userList.add(UserManager.getInstance().createAdmin("foo", "bar", "foo@bar.com"));
		userList.add(UserManager.getInstance().createUser("bar", "foo", "asd@asd.com"));
		userList.add(UserManager.getInstance().createUser("bob", "foo", "bsd@asd.com"));
	}
	
	private static void loadProduct() {
		productList.add(ProductManager.getInstance().createProduct("apple", 15.3));
		productList.add(ProductManager.getInstance().createProduct("banana", 23.4));
		productList.add(ProductManager.getInstance().createProduct("orange", 9.33));
		productList.add(ProductManager.getInstance().createProduct("peach", 13));
		productList.add(ProductManager.getInstance().createProduct("strawberry", 5.5));
	}

	public static List<UserEntity> getUsers(){
		return userList;
	}
	
	public static List<ProductEntity> getProducts(){
		return productList;
	}

	
	
	
}
