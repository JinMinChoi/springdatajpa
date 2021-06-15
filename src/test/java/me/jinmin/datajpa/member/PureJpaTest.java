package me.jinmin.datajpa.member;

import me.jinmin.datajpa.member.repository.MemberPureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PureJpaTest {

    @Autowired
    MemberPureRepository memberPureRepository;

    @Autowired
    EntityManager em;

    @Test
    public void name_age() throws Exception {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberPureRepository.save(m1);
        memberPureRepository.save(m2);

        List<Member> findMembers = memberPureRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(findMembers.get(0).getUsername()).isEqualTo("AAA");
        assertThat(findMembers.get(0).getAge()).isEqualTo(20);
    }

    @Test
    public void paging() throws Exception {
        memberPureRepository.save(new Member("member1", 10));
        memberPureRepository.save(new Member("member2", 10));
        memberPureRepository.save(new Member("member3", 10));
        memberPureRepository.save(new Member("member4", 10));
        memberPureRepository.save(new Member("member5", 10));

        int age = 10;
        int offset = 0;
        int limit = 3;

        List<Member> members = memberPureRepository.findByPage(age, offset, limit);
        long totalCount = memberPureRepository.countByAge(age);

        for (Member member : members) {
            System.out.println("member = " + member);
        }

        System.out.println("totalCount = " + totalCount);

        assertThat(members.size()).isEqualTo(3);
        assertThat(totalCount).isEqualTo(5);
    }

    @Test
    public void bulk() throws Exception {
        memberPureRepository.save(new Member("member1", 10));
        memberPureRepository.save(new Member("member2", 19));
        memberPureRepository.save(new Member("member3", 20));
        memberPureRepository.save(new Member("member4", 21));
        memberPureRepository.save(new Member("member5", 40));

        int resultCount = memberPureRepository.bulkAgePlus(20);

        em.flush();
        em.clear();

        List<Member> all = memberPureRepository.findAll();
        for (Member member : all) {
            System.out.println("member = " + member);
        }

    }


}