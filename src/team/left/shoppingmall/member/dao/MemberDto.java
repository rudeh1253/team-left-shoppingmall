package team.left.shoppingmall.member.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class MemberDto {
    private final String memberName;
    private final String profileImg;
}
