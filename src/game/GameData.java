package game;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameData {

    private Map<String,Object> data;

    public GameData() {
        data = new HashMap<>();
    }

    public void addData(String key, Object info) {
        if(info == null) return;
        if(data.containsKey(key)) {
            throw new IllegalArgumentException("This key was already added.");
        }
        data.put(key, info);
    }

    public Object get(String key) {
        return data.getOrDefault(key,null);
    }

    public Map<String,Object> getAll() {
        return Collections.unmodifiableMap(data);
    }

    public boolean isMatch(String key, Object info) {
        if(data.containsKey(key)) {
            return data.get(key).equals(info);
        }
        return false;
    }

    public boolean hasKey(String key) {
        return data.containsKey(key);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj instanceof GameData) {
            GameData gd = (GameData) obj;
            return data.equals(gd.data);
        }
        return false;
    }
}
