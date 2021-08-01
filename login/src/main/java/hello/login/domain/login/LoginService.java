package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null: 로그인 실패
     */
    public Member login(String username, String password) {
        return memberRepository.findByUsername(username)
                .filter(member -> member.getUsername().equals(password))
                .orElse(null);
    }
}
