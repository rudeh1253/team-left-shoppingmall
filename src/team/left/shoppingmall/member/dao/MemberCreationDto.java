package team.left.shoppingmall.member.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class MemberCreationDto {
    private String email;
    private String password;
    private String memberName;
    private String profileImg;
    private LocalDate birthDate;
    private String tel;
    private String address;
    private String gender;
    private String role;
    private String company;
    private String answer;
    
    public int getPoint() {
        return 0;
    }
    
    public String isDeleted() {
        return "N";
    }
}
