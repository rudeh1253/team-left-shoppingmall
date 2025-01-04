package team.left.shoppingmall.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMoneyEachYearDto {
	private String year;
	private String money;
}
