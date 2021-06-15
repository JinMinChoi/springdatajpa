package me.jinmin.datajpa.member.repository;

import me.jinmin.datajpa.member.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
