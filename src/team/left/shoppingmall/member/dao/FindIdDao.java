package team.left.shoppingmall.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class FindIdDao {
	private static DataSource dataSource = DataSourceContainer.getDataSource();

	public String findId(String name, String tel) throws SQLException {

		Connection conn = null;
		System.out.println(name + tel);
		String findedEmail = "";

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT email FROM member WHERE member_name = ? AND tel = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);

			ResultSet result = pstmt.executeQuery();
			System.out.println(result);
			if (result.next()) {
				findedEmail = result.getString("email");
			}

		} catch (Exception e) {
			throw new RuntimeException("멤버 찾기 실패 : " + e.getMessage());
		} finally {
			conn.close();
		}
		System.out.println(findedEmail);
		return findedEmail;
	}

}
