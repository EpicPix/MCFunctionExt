package ga.epicpix.mcfext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectorAccessible {
    enum Type {
        DOUBLE
    }

    Type value();
    boolean changeable() default false;
}
