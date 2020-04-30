package ru.job4j.concurrent.cache;

import java.util.concurrent.ConcurrentHashMap;

public class CASCache {
    private final ConcurrentHashMap<Integer, Base> cache;

    public CASCache(ConcurrentHashMap<Integer, Base> cache) {
        this.cache = cache;
    }

    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (k, v) -> {
            if (model.getVersion() != v.getVersion()) {
                throw new OptimisticException("Throw Exception in Thread");
            }
            int version = model.getVersion();
            v.setVersion(++version);
            return v;
        });
    }

    public Base delete(Base model) {
        return cache.remove(model.getId());
    }
}
