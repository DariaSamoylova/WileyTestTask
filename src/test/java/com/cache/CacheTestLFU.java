package com.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTestLFU {
    private Cache<TestObject> cache;
    private TestObject testObject0;
    private int maxCapasity = 5;

    @BeforeEach
    void setUp() {
        cache = new Cache<>(new LFUCache<>(maxCapasity));
        testObject0 = new TestObject("object #0", 0);
        cache.put(testObject0);
        for (int i = 1; i < maxCapasity; i++) {
            cache.put(new TestObject("object #" + i, i));
        }


    }

    @Test
    void put() {
        TestObject testObject = new TestObject("test put object", 999);
        cache.put(testObject);
        assertEquals(testObject, cache.get(testObject));
    }

    @Test
    void get() {
        TestObject testObject = cache.get(testObject0);
        assertEquals(testObject, testObject0);
    }

    @Test
    void remove() {
        cache.remove(testObject0);
        TestObject testObject = cache.get(testObject0);
        assertNull(testObject);

    }

    @Test
    void clear() {
        cache.clear();
        assertEquals(cache.size(), 0);
    }

    @Test
    void size() {
        assertEquals(cache.size(), maxCapasity);
    }


    @Test
    void cacheStrategy() {

        for (int y = 1; y < 10; y++) {
            for (int i = 1; i < maxCapasity; i++) {
                cache.get(new TestObject("object #" + i, i));
            }
        }
        assertNotNull(cache.get(testObject0));
        cache.put(new TestObject("new object", 123));
        assertNull(cache.get(testObject0));
    }
}