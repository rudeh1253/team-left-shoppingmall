package team.left.shoppingmall.product.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;
import team.left.shoppingmall.product.model.ProductDto;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class ProductDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();
    
    // 상품 등록
    public void insertProduct(ProductDto product) {
    	String sql = "INSERT INTO product VALUES(PRODUCT_PK_SEQ.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, null, 'N')";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
    	) {
			stmt.setInt(1, product.getSellerId());
			stmt.setString(2, product.getProductName());
			stmt.setString(3, product.getDescription());
			stmt.setInt(4, product.getPrice());
			stmt.setInt(5, product.getStock());
//			stmt.setString(6, product.getThumbnail());
			int count = stmt.executeUpdate();
			if (count <= 0) {
				throw new RuntimeException("행이 삽입되지 않았습니다.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end insertProduct
    
    // 상품 수정
    public void updateProduct(ProductDto product) {
    	String sql = "UPDATE product SET product_name=?, description=?, price=?, stock=?, thumbnail=null WHERE product_id=?";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
    	) {
    		stmt.setString(1, product.getProductName());
    		stmt.setString(2, product.getDescription());
    		stmt.setInt(3, product.getPrice());
    		stmt.setInt(4, product.getStock());
//			stmt.setString(5, product.getThumbnail());
    		stmt.setInt(5, product.getProductId());
    		int count = stmt.executeUpdate();
    		if (count <= 0) {
    			throw new RuntimeException("행이 수정되지 않았습니다.");
    		}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end updateProduct
    
    // 상품 삭제
    public void deleteProduct(int productId) {
    	String sql = "UPDATE product SET is_deleted='Y' WHERE product_id=?";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
    	) {
    		stmt.setInt(1, productId);
    		int count = stmt.executeUpdate();
    		if (count <= 0) {
    			throw new RuntimeException("행이 삭제되지 않았습니다.");
    		}			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end deleteProduct
    
    // 상품아이디로 상품 조회
    public ProductDto getProductById(int productId) {
    	String sql = "SELECT * FROM product WHERE product_id=? AND is_deleted = 'N'";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
    	) {
    		stmt.setInt(1, productId);
    		try (
				ResultSet rs = stmt.executeQuery();    				
    		) {
    			if (rs.next()) {
    				ProductDto product = new ProductDto();
    				product.setProductId(rs.getInt("product_id"));
    				product.setSellerId(rs.getInt("seller_id"));
    				product.setRegDate(rs.getDate("reg_date"));
    				product.setProductName(rs.getString("product_name"));
    				product.setDescription(rs.getString("description"));
    				product.setPrice(rs.getInt("price"));
    				product.setStock(rs.getInt("stock"));
    				product.setThumbnail(rs.getString("thumbnail"));
    				return product;
    			} else {
    				throw new RuntimeException("행이 조회되지 않았습니다.");
    			}
    		}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end getProductById

    // 상품아이디로 상품 스펙을 포함한 상품 상세 조회
	public ProductSpecDto getProductSpecById(int productId) {
	    String sql = "SELECT * "
	    		+"FROM product p "
	    		+"JOIN product_spec ps ON p.product_id = ps.product_id "
	    		+"WHERE p.product_id=? AND is_deleted = 'N'";
	    try (
    		Connection conn = dataSource.getConnection();
    		PreparedStatement pstmt = conn.prepareStatement(sql)
	    ) {
	        pstmt.setInt(1, productId); // 상품 ID 설정
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                // ResultSet에서 데이터를 읽어 ProductSpecDto로 매핑
	                ProductSpecDto productSpec = new ProductSpecDto();
	                productSpec.setProductId(rs.getInt("product_id"));
	                productSpec.setSellerId(rs.getInt("seller_id"));
	                productSpec.setRegDate(rs.getDate("reg_date"));
	                productSpec.setProductName(rs.getString("product_name"));
	                productSpec.setDescription(rs.getString("description"));
	                productSpec.setPrice(rs.getInt("price"));
	                productSpec.setStock(rs.getInt("stock"));
	                productSpec.setThumbnail(rs.getString("thumbnail"));
	                productSpec.setWeight(rs.getInt("weight"));
	                productSpec.setScreenSize(rs.getInt("screen_size"));
	                productSpec.setRefreshRate(rs.getString("refresh_rate"));
	                productSpec.setDisplayResolution(rs.getString("display_resolution"));
	                productSpec.setChipset(rs.getString("chipset"));
	                productSpec.setCameraResolution(rs.getString("camera_resolution"));
	                productSpec.setBatteryCapacity(rs.getInt("battery_capacity"));
	                return productSpec;
	            } else {
	                throw new RuntimeException("상품 ID에 해당하는 데이터를 찾을 수 없습니다: ID = " + productId);
	            }
	        } catch (Exception e) {
				throw new RuntimeException(e);
			}
	    } catch (Exception e) {
	    	throw new RuntimeException(e);
		}
	} // end getProductSpecById

	// 상품 목록 조회
    public List<ProductDto> getProductList() {
        List<ProductDto> productList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE is_deleted = 'N'";
        try (
    		Connection con = dataSource.getConnection();
    		PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery()
        ) {
        	while (rs.next()) {
        		ProductDto product = new ProductDto();
        		product.setProductId(rs.getInt("product_id"));
        		product.setSellerId(rs.getInt("seller_id"));
        		product.setRegDate(rs.getDate("reg_date"));
        		product.setProductName(rs.getString("product_name"));
        		product.setDescription(rs.getString("description"));
        		product.setPrice(rs.getInt("price"));
        		product.setStock(rs.getInt("stock"));
        		product.setThumbnail(rs.getString("thumbnail")); // 썸네일 추가
        		productList.add(product);
        	}
		} catch (Exception e) {
			throw new RuntimeException("Error fetching all products", e);
		}
        return productList;
    } // end showAllProducts
    
    // 상품 이름으로 상품 목록 가져오기
    public List<ProductDto> getProductsByName(String productName){
    	 List<ProductDto> productList = new ArrayList<>();
         String sql = "SELECT * FROM product WHERE is_deleted = 'N' AND product_name LIKE '%" + productName + "%'";
         
         try (
         		Connection con = dataSource.getConnection();
         		PreparedStatement stmt = con.prepareStatement(sql);
     			ResultSet rs = stmt.executeQuery()
             ) {
             	while (rs.next()) {
             		ProductDto product = new ProductDto();
             		product.setProductId(rs.getInt("product_id"));
             		product.setSellerId(rs.getInt("seller_id"));
             		product.setRegDate(rs.getDate("reg_date"));
             		product.setProductName(rs.getString("product_name"));
             		product.setDescription(rs.getString("description"));
             		product.setPrice(rs.getInt("price"));
             		product.setStock(rs.getInt("stock"));
             		product.setThumbnail(rs.getString("thumbnail")); // 썸네일 추가
             		productList.add(product);
             	}
     		}  catch (Exception e) {
     			throw new RuntimeException("Error fetching products by product name: " + productName, e);
     		}
         return productList;
    }
    
    // show Register product
    public List<ProductDto> getProductsBySellerId(int sellerId) {
        List<ProductDto> productList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE seller_id = ? AND is_deleted = 'N'";
        try (
    		Connection con = dataSource.getConnection();
    		PreparedStatement stmt = con.prepareStatement(sql);
		) {
        	stmt.setInt(1, sellerId);
        	try (
    			ResultSet rs = stmt.executeQuery();
        	) {
        		while (rs.next()) {
        			ProductDto product = new ProductDto();
        			product.setProductId(rs.getInt("product_id"));
        			product.setSellerId(rs.getInt("seller_id"));
        			product.setRegDate(rs.getDate("reg_date"));
        			product.setProductName(rs.getString("product_name"));
        			product.setDescription(rs.getString("description"));
        			product.setPrice(rs.getInt("price"));
        			product.setStock(rs.getInt("stock"));
        			product.setThumbnail(rs.getString("thumbnail")); // 썸네일 추가
        			productList.add(product);
        		}
        	}
		} catch (Exception e) {
			throw new RuntimeException("Error fetching products by seller ID " + sellerId, e);
		}
        return productList;
    } // end getProductsBySellerId
    
    // 상품아이디로 재고수 조회
    public int getProductStock(int productId) {
    	String sql = "SELECT stock FROM product WHERE product_id=? AND is_deleted = 'N'";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
    	) {
    		stmt.setInt(1, productId);
    		try (
				ResultSet rs = stmt.executeQuery();    				
    		) {
    			if (rs.next()) {
    				int count = rs.getInt("stock");
    				return count;
    			} else {
    				throw new RuntimeException("행이 조회되지 않았습니다.");
    			}
    		}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end getProductStock
    
    // 상품 재고수 수정
    public void setProductStock(int productId, int chageStock) {
    	int stock = getProductStock(productId);
    	stock -= chageStock;
    	String sql = "UPDATE product SET stock=? WHERE product_id=?";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt = con.prepareStatement(sql);
    	) {
    		stmt.setInt(1, stock);
    		stmt.setInt(2, productId);
    		int count = stmt.executeUpdate();
    		if (count <= 0) {
    			throw new RuntimeException("행이 수정되지 않았습니다.");
    		}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end setProductStock
}
