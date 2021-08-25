package hello.file.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@RestController
public class ServletUploadControllerV1 {

//    @PostMapping("/upload")
    public String saveFile1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName:{}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts:{}", parts);
        return "ok";
    }
}
