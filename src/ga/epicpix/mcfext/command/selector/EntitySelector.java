package ga.epicpix.mcfext.command.selector;

import ga.epicpix.mcfext.command.selector.SelectorAccessible.Type;
import ga.epicpix.mcfext.command.CommandStringIterator;
import java.lang.reflect.Field;

import static ga.epicpix.mcfext.command.selector.SelectorAccessible.Type.*;
import static ga.epicpix.mcfext.Utils.error;

public class EntitySelector extends Selector {

    public enum TargetSelector {
        NEAREST("r"),
        RANDOM("r"),
        PLAYERS("a"),
        ENTITIES("e"),
        SELF("s");

        private final String name;

        TargetSelector(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public static TargetSelector getTargetSelector(String sel) {
            if(sel.startsWith("@")) sel = sel.substring(1);
            for(TargetSelector target : values()) {
                if(target.getName().equals(sel)) {
                    return target;
                }
            }
            return null;
        }
    }

    private final TargetSelector targetSelector;

    public EntitySelector(TargetSelector sel) {
        targetSelector = sel;
    }

    @SelectorAccessible(DOUBLE) private Double x;
    @SelectorAccessible(DOUBLE) private Double y;
    @SelectorAccessible(DOUBLE) private Double z;

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("@").append(targetSelector.getName());
        builder.append("[");
        boolean has = false;
        try {
            for(Field f : EntitySelector.class.getDeclaredFields()) {
                f.setAccessible(true);
                if (f.isAnnotationPresent(SelectorAccessible.class)) {
                    if (f.get(this) != null) {
                        has = true;
                        builder.append(f.getName()).append("=").append(writeType(f.getAnnotation(SelectorAccessible.class).value(), f.get(this))).append(",");
                    }
                }
            }
        }catch(Exception e) {
            error("An error occurred");
            return null;
        }
        if(!has) {
            builder.append(",");
        }
        return builder.substring(0, builder.length() - 1) + "]";
    }

    private String writeType(Type type, Object data) {
        if(type == DOUBLE) {
            double num = (Double) data;
            if((int) num == num) {
                return Integer.toString((int) num);
            }
            return Double.toString(num);
        }else {
            return null;
        }
    }

    private Object readType(Type type, CommandStringIterator iter) {
        if(type == DOUBLE) {
            String val = "";
            while(iter.hasNext()) {
                char seek = iter.seek();
                if(seek == '.' || (seek>='0' && '9'>=seek)) {
                    if(val.contains(".") && seek == '.') {
                        break;
                    }else {
                        val += iter.nextChar();
                    }
                }else {
                    break;
                }
            }
            return Double.parseDouble(val);
        }
        return null;
    }

    void apply(String data) {
        if(!data.isEmpty()) {
            CommandStringIterator iter = new CommandStringIterator(data);
            boolean first = true;
            while(iter.hasNext()) {
                if(!first) {
                    if(iter.nextChar() != ',') {
                        error("Invalid selector");
                        return;
                    }
                }
                StringBuilder key = new StringBuilder();

                while(iter.hasNext()) {
                    char c = iter.nextChar();
                    if(c == '=') {
                        break;
                    }
                    key.append(c);
                }

                try {

                    Field f = EntitySelector.class.getDeclaredField(key.toString());
                    f.setAccessible(true);
                    if(f.isAnnotationPresent(SelectorAccessible.class)) {
                        SelectorAccessible anno = f.getAnnotation(SelectorAccessible.class);
                        if(!anno.changeable() && f.get(this) != null) {
                            error("Invalid selector");
                            return;
                        }else {
                            f.set(this, readType(anno.value(), iter));
                        }
                    }else {
                        error("Invalid selector");
                        return;
                    }
                }catch(Exception e) {
                    error("Invalid selector");
                    return;
                }

                first = false;
            }
        }
    }

    public TargetSelector getTargetSelector() {
        return targetSelector;
    }
}
