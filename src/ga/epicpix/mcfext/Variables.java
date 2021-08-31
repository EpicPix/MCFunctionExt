package ga.epicpix.mcfext;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public final class Variables {

    private final HashMap<String, Object> values = new HashMap<>();

    public void set(String key, Object val) {
        values.remove(key);
        values.put(key, val);
    }

    public Object get(String key) {
        return values.get(key);
    }

    public String getString(String key) {
        return (String) get(key);
    }

    public String placeVariables(String str) {
        if(!str.contains("$")) {
            return str;
        }
        String newStr = str;
        for(Entry<String, Object> entry : values.entrySet()) {
            newStr = newStr.replaceAll("\\{\\$" + Pattern.quote(entry.getKey()) + "}", entry.getValue().toString());
        }
        return newStr;
    }

}
