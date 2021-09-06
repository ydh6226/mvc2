package hello.study.controller;

import hello.study.argumentResolver.CurrentPerson;
import hello.study.controller.dto.DogResponse;
import hello.study.domain.Dog;
import hello.study.domain.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * ArgumentResolver -> header 사람 id 값을 받고, Person 객체 넘겨주자
 * Interceptor -> log
 * Converter
 */
@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public DogResponse registerDog(@RequestBody Dog dog, @CurrentPerson Person person) {
        checkDogName(dog);
        String dogInfo = String.format("주인:%s, 동물:%s", person.getName(), dog.getName());
        return new DogResponse(dogInfo, dog.getName());
    }

    private void checkDogName(Dog dog) {
        if (dog.getName().equals("돌돌이")) {
            throw new IllegalArgumentException("돌돌이는 안돼");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String handlerIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
