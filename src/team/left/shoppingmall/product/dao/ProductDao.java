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
    	Connection con = null;
    	PreparedStatement stmt = null;
    	try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO PRODUCT VALUES(PRODUCT_PK_SEQ.NEXTVAL, ?, SYSDATE, ?, ?, ?, ?, null, 'N')";
			stmt = con.prepareStatement(sql);
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
		} finally {
			closePreparedStatement(stmt);
			closeConnection(con);
		}
    } // end insertProduct
    
    // 상품 수정
    public void updateProduct(ProductDto product) {
    	Connection con = null;
    	PreparedStatement stmt = null;
    	try {
			con = dataSource.getConnection();
			String sql = "UPDATE PRODUCT SET PRODUCT_NAME=?, DESCRIPTION=?, PRICE=?, STOCK=?, THUMBNAIL=null WHERE PRODUCT_ID=?";
			stmt = con.prepareStatement(sql);
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
		} finally {
			closePreparedStatement(stmt);
			closeConnection(con);
		}
    } // end updateProduct
    
    // 상품 삭제
    public void deleteProduct(int productId) {
    	Connection con = null;
    	PreparedStatement stmt = null;
    	try {
			con = dataSource.getConnection();
			String sql = "UPDATE PRODUCT SET IS_DELETED='Y' WHERE PRODUCT_ID=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productId);
			int count = stmt.executeUpdate();
			if (count <= 0) {
				throw new RuntimeException("행이 삭제되지 않았습니다.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closePreparedStatement(stmt);
			closeConnection(con);
		}
    } // end deleteProduct
    
    private void closeConnection(Connection con) {
		if (con!=null) {
			try {
				con.close();
			} catch (Exception e) {}
		}
	} // end closeConnection
    
    private void closePreparedStatement(PreparedStatement stmt) {
    	if (stmt != null) {
    		try {
				stmt.close();
			} catch (Exception e) {}
    	}
    } // end closePreparedStatement
    
    public ProductDto selectProduct(int productId) {
    	ProductDto product = new ProductDto();
    	
    	Connection con = null;
    	PreparedStatement stmt = null;
    	try {
			con = dataSource.getConnection();
			String sql = "select * from product where product_id=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int pId = rs.getInt("product_id");
				int sellerId = rs.getInt("seller_id");
				Date regDate = rs.getDate("reg_date");
				String productName = rs.getString("product_name");
				String description = rs.getString("description");
				int price = rs.getInt("price");
				int stock = rs.getInt("stock");
				product.setProductId(productId);
				product.setSellerId(sellerId);
				product.setRegDate(regDate);
				product.setProductName(productName);
				product.setDescription(description);
				product.setPrice(price);
				product.setStock(stock);
			} else {
				throw new RuntimeException("행이 조회되지 않았습니다.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closePreparedStatement(stmt);
			closeConnection(con);
		}
    	
    	return product;
    }

    //show all product
    public List<ProductDto> showAllProducts() {
        List<ProductDto> productList = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            String sql = "SELECT * FROM product WHERE is_deleted = 'N'";
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

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
        } finally {
            closePreparedStatement(stmt);
            closeConnection(con);
        }

        return productList;
    }
    
    //show Register product
    public List<ProductDto> getProductsBySellerId(int sellerId) {
        List<ProductDto> productList = new ArrayList<>();

        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            String sql = "SELECT * FROM product WHERE seller_id = ? AND is_deleted = 'N'";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();

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
            throw new RuntimeException("Error fetching products by seller ID " + sellerId, e);
        } finally {
            closePreparedStatement(stmt);
            closeConnection(con);
        }

        return productList;
    }
    
 // 상품 상세 정보를 조회하는 메서드
    public ProductSpecDto getProductSpecById(int productId) throws SQLException {
        String sql = "SELECT * FROM product_spec WHERE product_id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, productId); // 상품 ID 설정
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // ResultSet에서 데이터를 읽어 ProductSpecDto로 매핑
                    ProductSpecDto productSpec = new ProductSpecDto();
                    productSpec.setProductId(rs.getInt("product_id"));
                    productSpec.setWeight(rs.getInt("weight"));
                    productSpec.setScreenSize(rs.getInt("screen_size"));
                    productSpec.setRefreshSize(rs.getInt("refresh_rate"));
                    productSpec.setDisplayResolution(rs.getInt("display_resolution"));
                    productSpec.setChipset(rs.getString("chipset"));
                    productSpec.setCameraResolution(rs.getInt("camera_resolution"));
                    productSpec.setBatteryCapacity(rs.getInt("battery_capacity"));
                    
                    return productSpec;
                } else {
                    throw new SQLException("상품 ID에 해당하는 데이터를 찾을 수 없습니다: ID = " + productId);
                }
            }
        }
    }
}
