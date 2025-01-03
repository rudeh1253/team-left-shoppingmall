package team.left.shoppingmall.purchase.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceiptDto {
	private String thumbnail;
	private String productName;
	private int amount;
	private int totalPrice;
	private Date purchaseDate;
	private String state;
}
