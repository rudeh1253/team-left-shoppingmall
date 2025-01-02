package team.left.shoppingmall.member.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class SelectMemberDto {
	private String email;
	private String password;
	private String member_name;
	private String profile_img;
	private String birth_date;
	private String tel;
	private String address;
	private String gender;
	private String point;
	private String role;
	private String company;
	private String answer;
}
