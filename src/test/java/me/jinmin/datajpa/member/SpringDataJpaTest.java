package me.jinmin.datajpa.member;

import me.jinmin.datajpa.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SpringDataJpaTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void paging2() throws Exception {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        Page<MemberDto> pageToDto = page.map(m -> new MemberDto(m.getId(), m.getUsername(), m.getTeam().getName()));

        List<MemberDto> content = pageToDto.getContent();
        long count = pageToDto.getTotalElements();

        for (MemberDto memberDto : content) {
            System.out.println("memberDto = " + memberDto);
        }

        assertThat(content.size()).isEqualTo(3);
        assertThat(count).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0); // 페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); //3(뽑은 자료), 2(나머지 자료) = 전체 페이지 수
        assertThat(page.isFirst()).isTrue(); // 첫번째 페이지인가
        assertThat(page.hasNext()).isTrue(); // 다음 페이지가 존재하는가
    }

}
