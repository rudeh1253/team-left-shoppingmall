package team.left.shoppingmall.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class CartDao {
    private static DataSource dataSource = DataSourceContainer.getDataSource();

	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<CartProductDto> showCart(int memberId) {
		List<CartProductDto> cartList = new ArrayList<>();
	    Connection con = null;
	    try {
	        con = dataSource.getConnection();
	        String sql = "select c.member_id, c.product_id, c.amount, p.seller_id, p.reg_date, p.product_name, "
	        		+ "p.description, p.price, p.stock, p.thumbnail \r\n" 
	        		+ "from cart c, product p "
	        		+ "where member_id=? and c.product_id=p.product_id";
	        PreparedStatement stmt = con.prepareStatement(sql);
	        stmt.setInt(1, memberId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) { // 여러 행을 처리
	            CartProductDto cart = new CartProductDto();
	            cart.setMemberId(rs.getInt("member_id"));
	            cart.setProductId(rs.getInt("product_id"));
	            cart.setAmount(rs.getInt("amount"));
	            cart.setSellerId(rs.getInt("seller_id"));
	            cart.setRegdate(rs.getDate("reg_date"));
	            cart.setProductName(rs.getString("product_name"));
	            cart.setDescription(rs.getString("description"));
	            cart.setPrice(rs.getInt("price"));
	            cart.setStock(rs.getInt("stock"));
	            cart.setThumbnail(rs.getString("thumbnail"));
	            cartList.add(cart);
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    } finally {
	        closeConnection(con);
	    }
	    
	    return cartList;
	}
	
	public void deleteAllCart(int memberId) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "delete from cart where member_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, memberId);
			int rowCount = stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			closeConnection(con);
		}
	}
	
	public void addCart(CartDto cart) {
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "insert into cart values(?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, cart.getMemberId());
			stmt.setInt(2, cart.getProductId());
			stmt.setInt(3, cart.getAmount());
			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "개 행이 추가되었습니다.");
			if (rowCount<=0) {
				throw new SQLException("저장된 행이 없습니다.");
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			closeConnection(con);
		}
	}
	
	
	public int increaseCartAmount(int productId) {
	    int updatedAmount = 0;
	    try (Connection con = dataSource.getConnection()) {
	        String sql = "UPDATE cart SET amount = amount + 1 WHERE product_id = ?";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, productId);
	            stmt.executeUpdate();
	        }

	        // Fetch updated amount
	        sql = "SELECT amount FROM cart WHERE product_id = ?";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, productId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    updatedAmount = rs.getInt("amount");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return updatedAmount;
	}

	public int decreaseCartAmount(int productId) {
	    int updatedAmount = 0;
	    try (Connection con = dataSource.getConnection()) {
	        String sql = "UPDATE cart SET amount = amount - 1 WHERE product_id = ? AND amount > 1";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, productId);
	            stmt.executeUpdate();
	        }

	        // Fetch updated amount
	        sql = "SELECT amount FROM cart WHERE product_id = ?";
	        try (PreparedStatement stmt = con.prepareStatement(sql)) {
	            stmt.setInt(1, productId);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    updatedAmount = rs.getInt("amount");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
	    return updatedAmount;
	}
	
	public boolean deleteProductFromCart(int productId) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = dataSource.getConnection();
            String sql = "DELETE FROM cart WHERE product_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, productId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // 성공적으로 삭제되면 true 반환
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	closeConnection(con);
        }
    }
	
	public int checkCart(int productId) {
        Connection con = null;
        PreparedStatement stmt = null;
        int count = 0;
        try {
            con = dataSource.getConnection();
            String sql = "select count(*) from cart WHERE product_id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
	        while (rs.next()) { // 여러 행을 처리
	            count = rs.getInt("count(*)");
	        }
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        throw new RuntimeException(e);
	    } finally {
	        closeConnection(con);
	    }
        return count;
        
    }
	
	private void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			}catch(Exception e) {
				//nothing
			}
		}
 	}
}
