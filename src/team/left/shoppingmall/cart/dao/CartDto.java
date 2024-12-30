package team.left.shoppingmall.cart.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
	private int memberId;
	private int productId;
	private int amount;
}
