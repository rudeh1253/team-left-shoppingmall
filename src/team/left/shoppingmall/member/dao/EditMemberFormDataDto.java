package team.left.shoppingmall.member.dao;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class EditMemberFormDataDto {
    private final Integer memberId;
    private final String email;
    private final String memberName;
    private final String profileImg;
    private final String tel;
    private final String address;
    private final LocalDate birthDate;
    private final String gender;
    private final String role;
    private final String company;
    private final String answer;
}
