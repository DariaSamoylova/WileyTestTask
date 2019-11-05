package com.cache;


public class Cache<K> {
    private final CacheStrategy<K> CACHE_STRATEGY;

    public Cache(CacheStrategy<K> cacheStrategy) {
        if (cacheStrategy == null) {
            throw new IllegalArgumentException("Cache strategy can't be null!");
        }
        CACHE_STRATEGY = cacheStrategy;

    }

    public void put(K object) {
        CACHE_STRATEGY.put(object, object);

    }

    public K get(K key) {
        return CACHE_STRATEGY.get(key);
    }

    public void remove(K key) {
        CACHE_STRATEGY.remove(key);
    }

    public void clear() {
        CACHE_STRATEGY.clear();
    }

    public int size() {
        return CACHE_STRATEGY.size();
    }
}
