package ecommerce;

import java.util.UUID;

public class UserManager {

	private static UserManager instance = new UserManager();
	
	private UserManager() {
		
	}
	
	public static UserManager getInstance() {
		return instance;
	}
	
	public UserEntity createUser(String username, String password, String email) {
		
		UserEntity user = new UserEntity();
		String uniqueID = UUID.randomUUID().toString();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setId(uniqueID);
		
		
		return user;
		
	}
	
	public AdminEntity createAdmin(String username, String password, String email) {
		
		AdminEntity user = new AdminEntity();
		String uniqueID = UUID.randomUUID().toString();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setId(uniqueID);
		
		
		return user;
		
	}
	
	
}
