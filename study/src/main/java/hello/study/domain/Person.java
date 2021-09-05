package hello.study.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@Getter
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor
public class Person {

    private long id;
    private String name;
    private String address;

    public Person(long id) {
        this.id = id;
        this.name = id + "번 사람";
    }
}
