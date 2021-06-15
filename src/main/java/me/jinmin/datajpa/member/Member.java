package me.jinmin.datajpa.member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jinmin.datajpa.team.Team;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member extends BaseEntityOnlyDate {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Member(String username) {
        this.username = username;
    }

    @Builder
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Builder
    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
