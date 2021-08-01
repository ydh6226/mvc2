package hello.login.domain.member;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemberRepositoryTest {

    private MemberRepository memberRepository = new MemberRepository();

    @Test
    public void filterTest() throws Exception {
        //given
        for (int i = 0; i < 3; i++) {
            memberRepository.save(new Member("user" + i, "user" + i, "user" + i));
        }

        //when
        List<Member> members = memberRepository.findAll();
        Optional<Member> findMember = members.stream()
                .filter(member -> {
                    System.out.println(member.getUsername());
                    return member.getUsername().equals("user0");
                })
                .findAny();

        //then
        System.out.println(findMember.get());
    }
}