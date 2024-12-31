package team.left.shoppingmall.cart.dao;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductDto {
	private int memberId;
	private int productId;
	private int amount;
	private int sellerId;
	private Date regdate;
	private String productName;
	private String description;
	private int price;
	private int stock;
	private String thumbnail;
}
