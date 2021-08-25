package hello.file.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@RestController
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String fileDir;

    @PostMapping("/upload")
    public String saveFile1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName:{}", itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts:{}", parts);

        for (Part part : parts) {
            log.info("==== Part ====");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {}: {}", headerName, part.getHeader(headerName));
            }
            //편의 메소드
            //Content-Disposition
            log.info("submittedFileName={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize()); part.getSize();

            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body={}", body);

            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("파일저장 path={}", fullPath);
                part.write(fullPath);
            }
        }

        return "ok";
    }
}
