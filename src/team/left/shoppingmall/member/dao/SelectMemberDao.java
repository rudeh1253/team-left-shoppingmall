package team.left.shoppingmall.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import team.left.shoppingmall.global.DataSourceContainer;

public class SelectMemberDao {
	
	private static DataSource dataSource = DataSourceContainer.getDataSource();
	
	public SelectMemberDto selectMember(SelectMemberDto member, int id) throws SQLException {
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sql = "SELECT * FROM member WHERE member_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);

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
