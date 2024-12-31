package team.left.shoppingmall.purchase.model;

import lombok.*;

@Data
@AllArgsConstructor
public class PurchaseProductDto {
	private int purchaseId;
	private int productId;
	private int amount;
	private int price;
}
