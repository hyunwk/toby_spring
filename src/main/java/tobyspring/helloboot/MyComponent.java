package tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 이 어노테이션이 언제까지 살아있을 건가
@Target(ElementType.TYPE)
@Component
// 메타 이노테이션. 이노테이션 위의 어노테잇녀
public @interface MyComponent {
}
