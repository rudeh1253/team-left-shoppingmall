package team.left.shoppingmall.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import team.left.shoppingmall.global.DataSourceContainer;
import team.left.shoppingmall.purchase.model.PurchaseDto;
import team.left.shoppingmall.purchase.model.PurchaseProductDto;

public class PurchaseDao {
    
	// 구매내역 저장
	public int insertPurchase(int totalPrice, String userid) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "INSERT INTO purchase VALUES (purchase_pk_seq.NEXTVAL, ?, sysdate, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  userid);
			pstmt.setString(2, "before");
			pstmt.setInt(3, totalPrice);
			
			rowCount = pstmt.executeUpdate();
			if(rowCount < 1) {
				throw new RuntimeException("구매 내역 저장 실패");
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			closeConnection(conn, pstmt);
		}
		
		return rowCount;
	}
	
	// 구매-상품내역 저장
	public int insertProductPurchase(PurchaseProductDto dto) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "INSERT INTO purchase_product VALUES (purchase_pk_seq.CURRVAL, )";
		}catch(SQLException e) {
			
		}
		
		return rowCount;
	}
	
	// 연결 끊기
	public void closeConnection(Connection conn, PreparedStatement pstmt) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
