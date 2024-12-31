package team.left.shoppingmall.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class FindPasswordDao {
	private static DataSource dataSource = DataSourceContainer.getDataSource();

	public String findPassword(String email, String tel) throws SQLException {

		Connection conn = null;
		String findedPassword = "";

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT password FROM member WHERE email = ? AND tel = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, tel);

			ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				findedPassword = result.getString("password");
			}

		} catch (Exception e) {
			throw new RuntimeException("멤버 찾기 실패 : " + e.getMessage());
		} finally {
			conn.close();
		}
		return findedPassword;
	}

}
