package team.left.shoppingmall.product.model;

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
		
		private int productId;
		
		private int weight;
		private int screenSize;
		
		private int refreshSize;
		
		private int displayResolution;
		@NonNull
		private String chipset;
		
		private int cameraResolution;
		private int batteryCapacity;
		
	
	
}
