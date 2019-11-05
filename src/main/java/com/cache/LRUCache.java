package com.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K> extends LinkedHashMap<K, K> implements CacheStrategy<K> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final boolean ACCESS_ORDER = true;
    private final int MAX_CAPACITY;

    public LRUCache(int maxCapacity) {
        super(maxCapacity, DEFAULT_LOAD_FACTOR, ACCESS_ORDER);
        MAX_CAPACITY = maxCapacity;

    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, K> eldest) {
        return size() > MAX_CAPACITY;
    }


}
