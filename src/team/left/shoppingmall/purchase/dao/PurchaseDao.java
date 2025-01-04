package team.left.shoppingmall.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.left.shoppingmall.global.DataSourceContainer;
import team.left.shoppingmall.member.dao.MemberDao;
import team.left.shoppingmall.product.model.ProductCountDto;
import team.left.shoppingmall.product.model.ProductDto;
import team.left.shoppingmall.purchase.model.PurchaseDetailDto;
import team.left.shoppingmall.purchase.model.PurchaseProductDto;
import team.left.shoppingmall.purchase.model.ReceiptDto;
import team.left.shoppingmall.purchase.model.ShipInfoDto;

public class PurchaseDao {
    
	// 구매내역 저장
	public int insertPurchase(ShipInfoDto shipInfo, int userid) {
		int rowCount = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "INSERT INTO purchase VALUES (purchase_pk_seq.NEXTVAL, ?, ?, sysdate, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			pstmt.setString(2, shipInfo.getAddress());
			pstmt.setString(3, "before");
			pstmt.setInt(4, shipInfo.getTotalPrice());
			pstmt.setString(5, shipInfo.getName());
			pstmt.setString(6, shipInfo.getTel());
			
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
			String sql = "SELECT pr.thumbnail AS thumbnail, pr.product_name AS product_name, "
					+ "pu.purchase_date AS purchase_date, pupr.amount AS amount, pu.total_price AS total_price, "
					+ "pu.state AS state, pr.product_id AS product_id, pu.purchase_id AS purchase_id "
					+ "FROM product pr "
					+ "JOIN purchase_product pupr ON pr.product_id = pupr.product_id "
					+ "JOIN purchase pu ON pupr.purchase_id = pu.purchase_id "
					+ "WHERE pu.buyer_id = ? "
					+ "ORDER BY pu.purchase_date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			ResultSet result = pstmt.executeQuery();
			
			while(result.next()) {
				ReceiptDto dto = new ReceiptDto(
					result.getInt("purchase_id"),
					result.getInt("product_id"),
					result.getString("thumbnail"),
					result.getString("product_name"),
					result.getInt("amount"),
					result.getInt("total_price"),
					result.getDate("purchase_date"),
					convertState(result.getString("state"))
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
			String sql = "SELECT pr.thumbnail AS thumbnail, pr.product_name AS product_name, "
					+ "pu.purchase_date AS purchase_date, pupr.amount AS amount, pu.total_price AS total_price, "
					+ "pu.state AS state, pr.product_id AS product_id, pu.purchase_id AS purchase_id "
					+ "FROM product pr "
					+ "JOIN purchase_product pupr ON pr.product_id = pupr.product_id "
					+ "JOIN purchase pu ON pupr.purchase_id = pu.purchase_id "
					+ "WHERE pr.seller_id = ? "
					+ "ORDER BY pu.purchase_date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			ResultSet result = pstmt.executeQuery();
			while(result.next()) {
				ReceiptDto dto = new ReceiptDto(
					result.getInt("purchase_id"),
					result.getInt("product_id"),
					result.getString("thumbnail"),
					result.getString("product_name"),
					result.getInt("amount"),
					result.getInt("total_price"),
					result.getDate("purchase_date"),
					convertState(result.getString("state"))
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
	
	// 배송 상세 정보 조회
	public PurchaseDetailDto findPurchaseDetail(int purchaseId, int productId) {
		PurchaseDetailDto detailDto = null;
		
		MemberDao memberDao = MemberDao.getInstance();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DataSourceContainer.getDataSource().getConnection();
			String sql = "SELECT pr.seller_id AS seller_id, pr.product_name AS product_name, pupr.amount AS amount, pupr.price as price, "
					+ "pu.buyer_id AS buyer_id, pu.purchase_date as purchase_date, pu.state as state, pu.name as getter_name,"
					+ "pu.address AS address, pu.tel AS tel "
					+ "FROM product pr "
					+ "JOIN purchase_product pupr ON pr.product_id = pupr.product_id "
					+ "JOIN purchase pu ON pupr.purchase_id = pu.purchase_id "
					+ "WHERE pupr.purchase_id = ? "
					+ "AND pupr.product_id = ? "
					+ "ORDER BY pu.purchase_date";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, purchaseId);
			pstmt.setInt(2, productId);
			
			ResultSet result = pstmt.executeQuery();
			if(result.next()) {
				detailDto = new PurchaseDetailDto(
					result.getString("product_name"),
					memberDao.findNameById(Integer.parseInt(result.getString("seller_id"))),
					result.getInt("amount"),
					result.getInt("price"),
					memberDao.findNameById(Integer.parseInt(result.getString("buyer_id"))),
					result.getDate("purchase_date"),
					convertState(result.getString("state")),
					result.getString("getter_name"),
					result.getString("tel"),
					result.getString("address")
				);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}finally {
			closeConnection(conn, pstmt);
		}
		
		return detailDto;
	}
	
	// 판매내역 이름, 갯수로 가져오기
	public List<ProductCountDto> getSellCount(int userid) {
	    List<ProductCountDto> list = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DataSourceContainer.getDataSource().getConnection();
	        
	        String sql = "SELECT pt.product_name,  SUM(pp.amount) AS count FROM purchase_product pp JOIN product pt ON pp.product_id = pt.product_id WHERE pt.seller_id = ? GROUP BY pt.product_name ";
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userid); 
	        ResultSet result = pstmt.executeQuery();
	        System.out.println(result);
	        while(result.next()) {
	        	ProductCountDto dto = new ProductCountDto();
	            dto.setProductName(result.getString("product_name"));
	            dto.setCount(result.getInt("count"));
	            list.add(dto);
	        }
	    } catch(SQLException e) {
	        throw new RuntimeException(e); 
	    } finally {
	        closeConnection(conn, pstmt);
	    }
	    
	    return list;
	}
	
	// 총 수익률
	public int getTotalMoney(int userid) {
	    int totalMoney = 0;
	    Connection conn = null;
	    PreparedStatement pstmt = null;

	    try {
	        conn = DataSourceContainer.getDataSource().getConnection();
	        
	        String sql = "SELECT SUM(pp.amount * pp.price) AS total_money FROM purchase_product pp JOIN product pt ON pp.product_id = pt.product_id WHERE pt.seller_id = ?";
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, userid); 
	        ResultSet result = pstmt.executeQuery();
	        System.out.println(result);
	        if(result.next()) {
	        	totalMoney = result.getInt("total_money");
	        }
	    } catch(SQLException e) {
	        throw new RuntimeException(e); 
	    } finally {
	        closeConnection(conn, pstmt);
	    }
	    
	    return totalMoney;
	}

	
	// 멤버 id로 배송정보 찾기
    public ShipInfoDto findShipInfoById(int memberId) {
    	ShipInfoDto shipInfo = null;
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
    		conn = DataSourceContainer.getDataSource().getConnection();
    		String sql = "SELECT member_name, address, tel FROM purchase WHERE member_id=?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, memberId);
    		ResultSet result = pstmt.executeQuery();
    		if(result.next()) {
    			shipInfo = new ShipInfoDto(
    				result.getString("member_name"),
					result.getString("address"),
					result.getString("tel"),
					0
				);
    		}
    	}catch(Exception e) {
    		throw new RuntimeException("멤버 포인트 업데이트 오류 발생");
    	}finally {
    		if(conn != null) {
    			try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    		
    		if(pstmt != null) {
    			try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	return shipInfo;
    }
	
	// 연결 끊기
	private void closeConnection(Connection conn, PreparedStatement pstmt) {
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
	
	// 배송 상태 데이터 변환
	private String convertState(String state) {
		String result = "";
		switch(state) {
			case "before":
				result = "배송 전";
				break;
			case "progress":
				result = "배송 중";
				break;
			case "done":
				result = "배송 완료";
				break;
			default:
				break;
		}
		
		return result;
	}
}
