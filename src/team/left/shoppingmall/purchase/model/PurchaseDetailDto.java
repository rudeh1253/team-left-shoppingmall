package team.left.shoppingmall.purchase.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PurchaseDetailDto {
	private String productName;
	private String sellerName;
	private int amount;
	private int price;
	private String buyerName;
	private Date purchaseDate;
	private String state;
	private String getterName;
	private String tel;
	private String address;
}
