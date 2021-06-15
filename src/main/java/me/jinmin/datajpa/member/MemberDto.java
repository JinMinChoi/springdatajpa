package me.jinmin.datajpa.member;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(of = {"id", "username", "teamname"})
public class MemberDto {

    private Long id;
    private String username;
    private String teamname;

    public MemberDto(Long id, String username, String teamname) {
        this.id = id;
        this.username = username;
        this.teamname = teamname;
    }
}
