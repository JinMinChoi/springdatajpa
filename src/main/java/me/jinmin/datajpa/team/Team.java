package me.jinmin.datajpa.team;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.jinmin.datajpa.member.Member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@ToString(of = {"id", "name"})
public class Team {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Builder
    public Team(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
