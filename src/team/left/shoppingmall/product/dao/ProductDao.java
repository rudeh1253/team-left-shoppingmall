package team.left.shoppingmall.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;
import team.left.shoppingmall.member.dao.SelectMemberDto;
import team.left.shoppingmall.product.model.ProductDto;
import team.left.shoppingmall.product.model.ProductSpecDto;

public class ProductDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();
    
    // 상품 등록
    public void insertProduct(ProductSpecDto product) {
    	String sql1 = "INSERT INTO product VALUES(PRODUCT_PK_SEQ.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, ?, 'N')";
    	String sql2 = "INSERT INTO product_spec VALUES(PRODUCT_PK_SEQ.CURRVAL, ?, ?, ?, ?, ?, ?, ?)";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
    	) {
			stmt1.setInt(1, product.getSellerId());
			stmt1.setString(2, product.getProductName());
			stmt1.setString(3, product.getDescription());
			stmt1.setInt(4, product.getPrice());
			stmt1.setInt(5, product.getStock());
			stmt1.setString(6, product.getThumbnail());
			
			stmt2.setInt(1, product.getWeight());
			stmt2.setInt(2, product.getScreenSize());
			stmt2.setString(3, product.getRefreshRate());
			stmt2.setString(4, product.getDisplayResolution());
			stmt2.setString(5, product.getChipset());
			stmt2.setString(6, product.getCameraResolution());
			stmt2.setInt(7, product.getBatteryCapacity());
			
			int count1 = stmt1.executeUpdate();
			int count2 = stmt2.executeUpdate();
			if (count1 <= 0 || count2 <= 0) {
				throw new RuntimeException("행이 삽입되지 않았습니다.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    } // end insertProduct
    
    // 상품 수정
    public void updateProduct(ProductSpecDto product) {
    	String sql1 = "UPDATE product SET product_name=?, description=?, price=?, stock=?, thumbnail=? WHERE product_id=?";
    	String sql2 = "UPDATE product_spec SET weight=?, screen_size=?, refresh_rate=?, display_resolution=?, chipset=?, camera_resolution=?, battery_capacity=? WHERE product_id=?";
    	try (
			Connection con = dataSource.getConnection();
	    	PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
    	) {
    		stmt1.setString(1, product.getProductName());
    		stmt1.setString(2, product.getDescription());
    		stmt1.setInt(3, product.getPrice());
    		stmt1.setInt(4, product.getStock());
			stmt1.setString(5, product.getThumbnail());
    		stmt1.setInt(6, product.getProductId());
    		
    		stmt2.setInt(1, product.getWeight());
    		stmt2.setInt(2, product.getScreenSize());
    		stmt2.setString(3, product.getRefreshRate());
    		stmt2.setString(4, product.getDisplayResolution());
    		stmt2.setString(5, product.getChipset());
    		stmt2.setString(6, product.getCameraResolution());
    		stmt2.setInt(7, product.getBatteryCapacity());
    		stmt2.setInt(8, product.getProductId());
    		
    		int count1 = stmt1.executeUpdate();
    		int count2 = stmt2.executeUpdate();
    		if (count1 <= 0 || count2 <= 0) {
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
        String sql = "SELECT * FROM product WHERE is_deleted = 'N' ORDER BY reg_date DESC";
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
    
    // 등록자 ID로 목록 조회
    public List<ProductDto> getProductList(int userid) {
        List<ProductDto> productList = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE is_deleted = 'N' AND seller_id=" + userid + " ORDER BY reg_date DESC";
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
    } 
    
    // 상품 이름으로 상품 목록 가져오기
    public List<ProductDto> getProductsByName(String productName){
    	 List<ProductDto> productList = new ArrayList<>();
         String sql = "SELECT * FROM product WHERE is_deleted = 'N' AND lower(product_name) LIKE lower('%" + productName + "%')";
         
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
    
    
    public SelectMemberDto showMember(int memberId) throws SQLException {
		
		Connection conn = null;
		SelectMemberDto member = new SelectMemberDto();

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM member WHERE member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, memberId);
			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				member.setEmail(result.getString("email"));
				member.setPassword(result.getString("password"));
				member.setMember_name(result.getString("member_name"));
				member.setProfile_img(result.getString("profile_img"));
				member.setBirth_date(result.getString("birth_date"));
				member.setTel(result.getString("tel"));
				member.setAddress(result.getString("address"));
				member.setGender(result.getString("gender"));
				member.setPoint(result.getString("point"));
				member.setRole(result.getString("role"));
				member.setCompany(result.getString("company"));
				member.setAnswer(result.getString("answer"));
			}

		} catch (SQLException e) {
			throw new SQLException("유저 찾기 실패 ");
		} finally {
			conn.close();
		}
		return member;
		
	}
}