package team.left.shoppingmall.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.left.shoppingmall.global.DataSourceContainer;
import team.left.shoppingmall.purchase.model.PurchaseProductDto;
import team.left.shoppingmall.purchase.model.ReceiptDto;

public class PurchaseDao {
    
	// 구매내역 저장
	public int insertPurchase(int totalPrice, int userid) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "INSERT INTO purchase VALUES (purchase_pk_seq.NEXTVAL, ?, sysdate, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  userid);
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
	public int insertPurchaseProduct(PurchaseProductDto dto) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "INSERT INTO purchase_product VALUES (purchase_pk_seq.CURRVAL, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getProductId());
			pstmt.setInt(2, dto.getAmount());
			pstmt.setInt(3, dto.getPrice());
			
			rowCount = pstmt.executeUpdate();
			if(rowCount < 1) {
				throw new RuntimeException("구매_상품 내역 저장 실패");
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			closeConnection(conn, pstmt);
		}
		
		return rowCount;
	}
	
	// 구매 내역 가져오기
	public List<ReceiptDto> getPurchaseReceipt(int userid){
		List<ReceiptDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "SELECT pr.thumbnail, pr.product_name, pr.price, pupr.amount, pupr.price, pu.state "
					+ "FROM product pr "
					+ "LEFT JOIN purchase_product pupr ON pr.product_id = pupr.product_id "
					+ "LEFT JOIN purchase pu ON pupr.purchase_id = pu.purchase_id "
					+ "WHERE pu.buyer_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ReceiptDto dto = new ReceiptDto(
					result.getString("pr.thumbnail"),
					result.getString("pr.product_name"),
					result.getInt("pr.price"),
					result.getInt("pupr.amont"),
					result.getInt("pupr.price"),
					result.getString("pu.state")
				);
				list.add(dto);
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			closeConnection(conn, pstmt);
		}
		
		return list;
	}
	
	// 판매 내역 가져오기
	public List<ReceiptDto> getSellReceipt(int userid){
		List<ReceiptDto> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "SELECT pr.thumbnail, pr.product_name, pr.price, pupr.amount, pupr.price, pu.state "
					+ "FROM product pr "
					+ "LEFT JOIN purchase_product pupr ON pr.product_id = pupr.product_id "
					+ "LEFT JOIN purchase pu ON pupr.purchase_id = pu.purchase_id "
					+ "WHERE pr.seller_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ReceiptDto dto = new ReceiptDto(
					result.getString("pr.thumbnail"),
					result.getString("pr.product_name"),
					result.getInt("pr.price"),
					result.getInt("pupr.amont"),
					result.getInt("pupr.price"),
					result.getString("pu.state")
				);
				list.add(dto);
			}
			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			closeConnection(conn, pstmt);
		}
		
		return list;
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
