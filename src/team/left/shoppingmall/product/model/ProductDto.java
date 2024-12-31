package team.left.shoppingmall.product.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDto {
	@NonNull
	private int productId;
	@NonNull
	private int sellerId;
	private Date regDate;
	@NonNull
	private String productName;
	@NonNull
	private String description;
	@NonNull
	private int price;
	@NonNull
	private int stock;
	private String thumbnail;
	
	public ProductDto(int productId, String productName, String description, int price, int stock) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.sellerId = 0;
	}
}
