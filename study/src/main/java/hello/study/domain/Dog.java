package hello.study.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Dog {

    private String name;

    public Dog() {
        log.info("[Dog Constructor]");
    }
}
