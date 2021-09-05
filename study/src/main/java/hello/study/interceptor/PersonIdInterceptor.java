package hello.study.interceptor;

import hello.study.argumentResolver.PersonArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class PersonIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("[preHandle] requestURI : {}", request.getRequestURI());

        String personIdHeader = request.getHeader(PersonArgumentResolver.PERSON_ID_HEADER);

        if (!(StringUtils.hasText(personIdHeader) && personIdHeader.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException("요청헤더가 올바르지 않음.");
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[afterCompletion] requestURI : {}", request.getRequestURI());
    }
}
