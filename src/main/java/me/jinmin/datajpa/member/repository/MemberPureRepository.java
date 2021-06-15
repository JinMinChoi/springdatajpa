package me.jinmin.datajpa.member.repository;

import lombok.RequiredArgsConstructor;
import me.jinmin.datajpa.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPureRepository {

    private final EntityManager em;

    //저장
    public void save(Member member) {
        em.persist(member);
    }

    //삭제
    public void delete(Member member) {
        em.remove(member);
    }

    //전체 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //단건 조회 Optional
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    //조회 갯수
    public long count(){
        return em.createQuery("select count(m) from Member m", Long.class)
                .getSingleResult();
    }

    public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
        return em.createQuery("select m from Member m" +
                " where m.username = :username and m.age > :age", Member.class)
                .setParameter("username", username)
                .setParameter("age", age)
                .getResultList();
    }

    public List<Member> findByPage(int age, int offset, int limit) {
        return em.createQuery("select m from Member m" +
                " where m.age = :age order by m.username desc", Member.class)
                .setParameter("age", age)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public long countByAge(int age) {
        return em.createQuery("select count(m) from Member m" +
                " where m.age = :age", Long.class)
                .setParameter("age", age)
                .getSingleResult();
    }

    public int bulkAgePlus(int age) {
        int updateCnt = em.createQuery(
                "update Member m set m.age = m.age + 1" +
                " where m.age >= :age")
                .setParameter("age", age)
                .executeUpdate();

        return updateCnt;
    }
}
