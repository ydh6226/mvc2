package hello.study.controller.dto;

import lombok.Getter;

@Getter
public class Response {
    private String dogInfo;
    private String dogName;

    public Response(String dogInfo, String dogName) {
        this.dogInfo = dogInfo;
        this.dogName = dogName;
    }
}
