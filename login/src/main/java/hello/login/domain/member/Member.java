package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public Member(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }
}
