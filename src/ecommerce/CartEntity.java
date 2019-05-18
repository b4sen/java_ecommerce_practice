package ecommerce;

import java.util.ArrayList;
import java.util.List;

public class CartEntity {
	private String id;
	private UserEntity owner;
	private List<ProductEntity> contents = new ArrayList<ProductEntity>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public UserEntity getOwner() {
		return owner;
	}
	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}
	public List<ProductEntity> getContents() {
		return contents;
	}
	public void setContents(List<ProductEntity> contents) {
		this.contents = contents;
	}
	
	public void addContents(ProductEntity e) {
		contents.add(e);
		
	}

	
	
}
