package team.left.shoppingmall.member.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString
public class MemberEditDto {
    private final String memberName;
    private final String address;
    private final LocalDate birthDate;
    private final String tel;
    private final String gender;
    private final String company;
    private final String role;
    private final String answer;
    private final String profileImg;
}
