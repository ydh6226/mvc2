package hello.study.controller.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class DogResponse {
    private String dogInfo;
    private String dogName;

    public DogResponse(String dogInfo, String dogName) {
        this.dogInfo = dogInfo;
        this.dogName = dogName;
    }

    @JsonGetter(value = "dogInfo")
    public String getDogInfo() {
        log.info("[ObjectMapper called DogResponse getter]");
        return dogInfo;
    }
}
