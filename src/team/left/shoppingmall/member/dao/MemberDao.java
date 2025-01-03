package team.left.shoppingmall.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import team.left.shoppingmall.global.DataSourceContainer;
import team.left.shoppingmall.global.JdbcSupport;
import team.left.shoppingmall.global.MapUtil;

public class MemberDao {
    private static final MemberDao singleton = new MemberDao();
    
    private MemberDao() {
    }
    
    public static MemberDao getInstance() {
        return singleton;
    }
    
    /**
     * 회원을 저장한다.
     * 
     * @return auto-generated key
     */
    public int insertMember(MemberCreationDto dto) {
        System.out.println("dto=" + dto);
        
        String sql = "INSERT INTO member "
                + "(member_id, email, password, member_name, profile_img, birth_date, tel, address, gender, point, role, company, answer, is_deleted) "
                + "VALUES "
                + "(member_pk_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Map<Integer, Object> params = new HashMap<>();
        
        params.put(1, dto.getEmail());
        params.put(2, dto.getPassword());
        params.put(3, dto.getMemberName());
        params.put(4, dto.getProfileImg());
        params.put(5, dto.getBirthDate());
        params.put(6, dto.getTel());
        params.put(7, dto.getAddress());
        params.put(8, dto.getGender());
        params.put(9, dto.getPoint());
        params.put(10, dto.getRole());
        params.put(11, dto.getCompany());
        params.put(12, dto.getAnswer());
        params.put(13, dto.isDeleted());
        
        int autogeneratedKey = JdbcSupport.insertOne(sql, params, "member_id");
        System.out.println("autogeneratedKey=" + autogeneratedKey);
        return autogeneratedKey;
    }
    
    public Optional<Integer> findMemberIdByEmailPassword(String email, String password) throws NoSuchElementException {
        String sql = "SELECT member_id FROM member WHERE email = ? AND password = ?";
        
        Map<String, Object> result = JdbcSupport.selectOne(sql, MapUtil.of(new Integer[] { 1, 2 }, new String[] { email, password }));
        System.out.println("result=" + result); 
        
        return Optional.ofNullable((Integer) result.get("member_id"));
    }
    
    public void updateMember(Integer memberId, MemberEditDto dto) {
        System.out.println("dto=" + dto);
        String sql = "UPDATE member "
                + "SET member_name = ?, "
                + "    address = ?, "
                + "    birth_date = ?, "
                + "    tel = ?, "
                + "    gender = ?, "
                + "    company = ?, "
                + "    role = ?, "
                + "    answer = ?, "
                + "    profile_img = ? "
                + "WHERE member_id = ?";
        
        JdbcSupport.update(sql, MapUtil.getParamsOf(new Object[] {
                dto.getMemberName(),
                dto.getAddress(),
                dto.getBirthDate(),
                dto.getTel(),
                dto.getGender(),
                dto.getCompany(),
                dto.getRole(),
                dto.getAnswer(),
                dto.getProfileImg(),
                memberId
        }));
    }
    
    public void updatePasswordByMemberId(String newPassword, Integer memberId) {
        String sql = "UPDATE member "
                + "SET password = ? "
                + "WHERE member_id = ?";
        JdbcSupport.update(sql, MapUtil.getParamsOf(newPassword, memberId));
    }
    
    public boolean existsByMemberIdAndPassword(Integer memberId, String password) {
        String sql = "SELECT COUNT(*) AS count "
                + "FROM member "
                + "WHERE member_id = ? AND password = ?";
        System.out.println(MapUtil.getParamsOf(memberId, password));
        Map<String, Object> queryResult = JdbcSupport.selectOne(sql, MapUtil.getParamsOf(memberId, password));
        
        return ((Integer) queryResult.get("count")) > 0;
    }
    
    public void deleteByMemberId(Integer memberId) {
        String sql = "UPDATE member "
                + "SET is_deleted = 'Y' "
                + "WHERE member_id = ?";
        
        JdbcSupport.update(sql, MapUtil.getParamsOf(memberId));
    }
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public Optional<EditMemberFormDataDto> findEditMemberFormDataById(Integer memberId) {
        String sql = "SELECT "
                + "    member_id, "
                + "    email, "
                + "    member_name, "
                + "    profile_img, "
                + "    TO_CHAR(birth_date, 'yyyy-MM-dd') AS birth_date, "
                + "    tel, "
                + "    address, "
                + "    gender, "
                + "    role, "
                + "    company, "
                + "    answer "
                + "FROM member "
                + "WHERE member_id = ? "
                + "    AND is_deleted = 'N'";
        
        try {
            Map<String, Object> result = JdbcSupport.selectOne(sql, MapUtil.of(1, memberId));
            EditMemberFormDataDto resultDto = EditMemberFormDataDto.builder()
                    .memberId((Integer) result.get("member_id"))
                    .email((String) result.get("email"))
                    .memberName((String) result.get("member_name"))
                    .profileImg((String) result.get("profile_img"))
                    .tel((String) result.get("tel"))
                    .address((String) result.get("address"))
                    .birthDate(LocalDate.parse((CharSequence) result.get("birth_date"), formatter))
                    .gender((String) result.get("gender"))
                    .role((String) result.get("role"))
                    .company((String) result.get("company"))
                    .answer((String) result.get("answer"))
                    .build();
            return Optional.of(resultDto);
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
    
    public String findMemberNameByMemberId(Integer memberId) {
        String sql = "SELECT member_name FROM member_id = ?";
        return (String) JdbcSupport.selectOne(sql, MapUtil.getParamsOf(memberId)).get("member_name");
    }
    
    public Optional<String> findEmailByMemberNameAndTel(String name, String tel) {
        String sql = "SELECT email FROM member WHERE member_name = ? AND tel = ?";
        
        try {
            return Optional.of((String) JdbcSupport.selectOne(sql, MapUtil.getParamsOf(name, tel)).get("email"));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
    
    public Optional<String> findPasswordByEmailAndTel(String email, String tel) {
        String sql = "SELECT password FROM member WHERE email = ? AND tel = ?";

        try {
            return Optional.of((String) JdbcSupport.selectOne(sql, MapUtil.getParamsOf(email, tel)).get("password"));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        } 
    }
    
    public String findRoleByMemberId(Integer memberId) {
        String sql = "SELECT role FROM member WHERE member_id = ?";
        return (String) JdbcSupport.selectOne(sql, MapUtil.getParamsOf(memberId)).get("role");
    }

    // 멤버 포인트 업데이트하기
    public int updatePointByMemberId(int sellerId, int price) {
    	int rowCount = 0;
    	
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	try {
    		conn = DataSourceContainer.getDataSource().getConnection();
    		String sql = "UPDATE member SET point=point+" + price + " WHERE member_id=" + sellerId;
    		System.out.println(sql);
    		pstmt = conn.prepareStatement(sql);
    		rowCount = pstmt.executeUpdate();
    		
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
    	
    	return rowCount;
    }
    
    public String findPasswordByMemberId(Integer memberId) {
        String sql = "SELECT password FROM member WHERE member_id = ?";
        return (String) JdbcSupport.selectOne(sql, MapUtil.getParamsOf(memberId)).get("password");
    }
}
