package team.left.shoppingmall.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceiptDto {
	private String thumbnail;
	private String productName;
	private int price;
	private int amount;
	private int totalPrice;
	private String state;
}
