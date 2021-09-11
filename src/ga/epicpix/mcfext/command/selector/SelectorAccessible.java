package ga.epicpix.mcfext.command.selector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectorAccessible {
    enum Type {
        DOUBLE,
        WORD
    }

    Type value();
    boolean changeable() default false;
}
