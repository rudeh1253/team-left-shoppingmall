package team.left.shoppingmall.cart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	public void showCart(int memberId) {
		CartDto cart = new CartDto();
		Connection con = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT * FROM cart WHERE member_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, memberId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				cart.setMemberId(memberId);
				cart.setProductId(rs.getInt("product_id"));
				cart.setAmount(rs.getInt("amount"));
			}else {
				throw new RuntimeException("아이디가 없습니다.");
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} finally {
			closeConnection(con);
		}
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
