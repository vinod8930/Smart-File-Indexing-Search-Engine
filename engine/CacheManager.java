package engine;

import model.FileRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {

    private Map<String, List<FileRecord>> cache = new HashMap<>();

    public boolean contains(String query) {
        return cache.containsKey(query);
    }

    public List<FileRecord> get(String query) {
        return cache.get(query);
    }

    public void put(String query, List<FileRecord> result) {
        cache.put(query, result);
    }
}
