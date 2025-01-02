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
public class ProductSpecDto {

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
	@NonNull
	private String thumbnail;
	@NonNull
	private int weight;
	@NonNull
	private int screenSize;
	@NonNull
	private String refreshRate;
	@NonNull
	private String displayResolution;
	@NonNull
	private String chipset;
	@NonNull
	private String cameraResolution;
	@NonNull
	private int batteryCapacity;
}