package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
public class WalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            log.info("hello 왈왈");
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("bye 왈왈");
        }
    }
}
