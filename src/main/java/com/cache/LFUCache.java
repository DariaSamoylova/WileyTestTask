package com.cache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class LFUCache<K> implements CacheStrategy<K> {
    private final int MAX_CAPACITY;
    private HashMap<K, K> cache;
    private HashMap<K, Long> objectFrequency;
    private TreeMap<Long, HashSet<K>> sortedObjectFrequency;

    public LFUCache(int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
        cache = new HashMap<>();
        objectFrequency = new HashMap<>();
        sortedObjectFrequency = new TreeMap<>();

    }

    @Override
    public K put(K key, K value) {


        if (cache.size() == MAX_CAPACITY) {
            K keyToDelete = sortedObjectFrequency.firstEntry().getValue().iterator().next();
            remove(keyToDelete);
        }
        K oldValue = cache.put(key, value);
        Long frequency = objectFrequency.computeIfAbsent(key, k -> 1L);
        sortedObjectFrequency.computeIfAbsent(frequency, k -> new HashSet<>()).add(key);
        return oldValue;
    }


    @Override
    public K get(K key) {
        K result = cache.get(key);
        if (result != null) {
            Long frequency = objectFrequency.compute(key, (k, v) -> v + 1L);
            sortedObjectFrequency.get(frequency - 1).remove(key);
            if (sortedObjectFrequency.get(frequency - 1).size() == 0)
                sortedObjectFrequency.remove(frequency - 1);

            sortedObjectFrequency.computeIfAbsent(frequency, k -> new HashSet<>()).add(key);

        }
        return result;
    }

    @Override
    public K remove(Object key) {
        if (cache.remove(key) != null) {
            Long frequency = objectFrequency.remove(key);
            sortedObjectFrequency.get(frequency).remove(key);
            if (sortedObjectFrequency.get(frequency).size() == 0)
                sortedObjectFrequency.remove(frequency);
        }

        return null;
    }

    @Override
    public void clear() {
        cache.clear();
        objectFrequency.clear();
    }


    @Override
    public int size() {
        return cache.size();
    }

}
