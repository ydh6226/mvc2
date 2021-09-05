package hello.study.argumentResolver;

import hello.study.domain.Person;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class PersonArgumentResolver implements HandlerMethodArgumentResolver {

    public static final String PERSON_ID_HEADER = "personId";

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentPerson.class) &&
                Person.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return new Person(findPersonId(webRequest));
    }

    private long findPersonId(NativeWebRequest webRequest) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return Long.parseLong(request.getHeader(PERSON_ID_HEADER));
    }
}
