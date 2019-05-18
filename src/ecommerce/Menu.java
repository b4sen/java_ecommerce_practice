package ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;


public class Menu {

	public static List<UserEntity> userList = new ArrayList<UserEntity>();
	public static List<ProductEntity> productList = new ArrayList<ProductEntity>();
	public static Scanner scan = new Scanner(System.in);
	public static HashMap<UserEntity, CartEntity> cart = new HashMap<UserEntity, CartEntity>();

	public static void main(String[] args) {
		DataStore.loadData();
		userList = DataStore.getUsers();
		productList = DataStore.getProducts();
		UserEntity user;
		boolean loggedin = true;
		

		System.out.println(" ");
		try {
			while (loggedin) {
				user = login();

				System.out.println(" ");
				System.out.println("Logged in as: " + user.getUsername());
				int menu = -1;
				boolean exit = false;
				if (user.getClass().getSimpleName().equalsIgnoreCase("UserEntity")) {
					CartEntity contents;
					if (!cart.containsKey(user)) {
						contents = new CartEntity();
						String id = UUID.randomUUID().toString();
						contents.setId(id);
						cart.put(user, contents);
					} else {
						contents = cart.get(user);
					}
					while (!exit) {
						System.out.println("Chose one option: ");
						System.out.println("1. Show products");
						System.out.println("2. Add products to cart");
						System.out.println("3. Show cart");
						System.out.println("0. Exit");
						menu = scan.nextInt();
						
						switch (menu) {
						case 1:
							System.out.println("You can chose from the following products: ");
							showProds();
							System.out.println();
							break;
						case 2:
							boolean found = false;
							showProds();
							ProductEntity p;
							String fruit = scan.next();
							for(int i = 0; i < productList.size(); i++) {
								if(productList.get(i).getProductName().equalsIgnoreCase(fruit)) {
									p = productList.get(i);
									contents.addContents(p);
									found = true;
								}
							}
							if(!found) {
								System.out.println("Fruit not found!");
							}
							
							break;
						case 3:
							List<ProductEntity> content = contents.getContents();
							// Printing the occurrences with prices
							Set<ProductEntity> unique = new HashSet<ProductEntity>(content);
							for(ProductEntity key : unique) {
								System.out.println(Collections.frequency(content, key) + "x " + key.getProductName() + ", $" + (Collections.frequency(content, key)*key.getProductPrice()) );
							}
							System.out.println();
							
							break;
						case 0:
							System.out.println("Exiting...");
							exit = true;
							scan.nextLine(); // handle the next line -> while loop would recognize enter as the next character! THIS IS MANDATORY!
							break;
						default:
							System.out.println("Unknown error has occured");
							break;
						}

					}
				}

				if (user.getClass().getSimpleName().equalsIgnoreCase("AdminEntity")) {
					while (!exit) {
						System.out.println("Chose one option: ");
						System.out.println("1. Show products");
						System.out.println("2. Add product");
						System.out.println("3. Remove product");
						System.out.println("0. Exit");
						menu = scan.nextInt();

						switch (menu) {
						case 1:
							System.out.println("You can chose from the following products: ");
							showProds();
							System.out.println();
							break;
						case 2:
							System.out.print("Product name: ");
							String name = scan.next();
							System.out.print("Product price: ");
							double price = scan.nextDouble();
							addProd(name, price);
							showProds();
							System.out.println();
							break;
						case 3:
							removeProd();
							showProds();
							System.out.println();
							break;
						case 0:
							System.out.println("Exiting...");
							scan.nextLine();
							exit = true;
							break;
						default:
							System.out.println("Unknown error has occured");
							break;
						}

					}
				}
			}

		} catch (NullPointerException e) {

		}

	}

	public static void removeProd() {
		String name = AdminEntity.removeProduct();
		productList.removeIf(obj -> obj.getProductName().equalsIgnoreCase(name));
	}

	public static void addProd(String productName, double productPrice) {
		boolean found = false;
		ProductEntity prod = new ProductEntity();
		String productId;
		productId = UUID.randomUUID().toString();
		prod.setProductId(productId);
		prod.setProductName(productName);
		prod.setProductPrice(productPrice);
		
		for (int i = 0; i < userList.size(); i++) {
			if (prod.getProductName().equalsIgnoreCase(productList.get(i).getProductName())) {
				System.out.println("Product is already in the list");
				found = true;
				break;
			}
		}
		if (!found) {
			productList.add(prod);
		}
		
	}

	public static void register() {
		boolean found = false;
		System.out.print("Username: ");
		String username = scan.nextLine();
		System.out.print("Password: ");
		String password = scan.nextLine();
		System.out.print("Email: ");
		String email = scan.nextLine();
		UserEntity user = UserManager.getInstance().createUser(username, password, email);

		for (int i = 0; i < userList.size(); i++) {
			if (user.getUsername().equalsIgnoreCase(userList.get(i).getUsername())) {
				System.out.println("Username is already in use");
				found = true;
				break;
			}
		}
		if (!found) {
			userList.add(user);
		}
	}

	public static UserEntity login() {
		System.out.print("Please enter your username: ");
		String username = scan.nextLine();
		System.out.print("Please enter your password: ");
		String password = scan.nextLine();

		try {
			UserEntity user = userList.stream().filter(o -> o.getUsername().equalsIgnoreCase(username)).findFirst()
					.get();

			if (user.getPassword().equalsIgnoreCase(password)) {

				System.out.println("Login successful!");
				return user;
			} else {
				System.out.println("Password incorrect.");
			}

		} catch (NoSuchElementException e) {
			System.out.println("User not found!");
		}
		return null;

	}

	public static void showProds() {
		for (int i = 0; i < productList.size(); i++) {
			System.out
					.println(productList.get(i).getProductName() + ", " + productList.get(i).getProductPrice() + "€ ");
		}
	}
}
