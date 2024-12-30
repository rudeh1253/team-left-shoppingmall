package team.left.shoppingmall.purchase.model;

import java.util.Date;
import java.util.List;
import lombok.*;

@Data
@AllArgsConstructor
public class PurchaseDto {
	private int purchaseId;
	private Date purchaseDate;
	private String state;
	private int total_price;
}
