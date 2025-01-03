package team.left.shoppingmall.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShipInfoDto {
	private String name;
	private String address;
	private String tel;
	private int totalPrice;
}
