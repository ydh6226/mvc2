package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid LoginForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getUsername(), form.getPassword());
        if (loginMember == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다");
            return "login/loginForm";
        }

        // TODO: [2021/08/02 양동혁] 로그인 성공처리
        return "redirect:/";
    }
}
