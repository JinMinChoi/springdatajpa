package me.jinmin.datajpa.member.repository;

import me.jinmin.datajpa.member.Member;
import me.jinmin.datajpa.member.MemberDto;
import me.jinmin.datajpa.member.UsernameDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUsername(String username);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<String> findUser(@Param("username") String username,
                          @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new me.jinmin.datajpa.member.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    List<UsernameDto> findProjectionByUsername(@Param("username") String username);
}
