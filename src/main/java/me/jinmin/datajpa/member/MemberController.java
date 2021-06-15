package me.jinmin.datajpa.member;

import lombok.RequiredArgsConstructor;
import me.jinmin.datajpa.member.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members1/{id}")
    public Member findMember(@PathVariable("id") Long id) {
        Member member = memberRepository.findById(id).get();
        return member;
    }

    @GetMapping("/members2/{id}")
    public Member findMember2(@PathVariable("id") Member member) {
        return member;
    }

    @GetMapping("/members")
    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @PostConstruct
    private void init() {
        for (int i = 1; i <= 100; i++) {
            memberRepository.save(new Member("member" + i, i));
        }
    }
}
