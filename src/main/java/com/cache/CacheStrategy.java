package com.cache;


public interface CacheStrategy<K> {
    K put(K key, K value);

    K get(K key);

    K remove(Object key);

    void clear();

    int size();
}
