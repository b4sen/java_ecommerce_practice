package ecommerce;

public class ProductEntity {

	private String productName;
	private double productPrice;
	private String productId;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "ProductEntity [productName=" + productName + ", productPrice=" + productPrice + ", productId=" + productId + "]";
	}
	
	
	
	
}
