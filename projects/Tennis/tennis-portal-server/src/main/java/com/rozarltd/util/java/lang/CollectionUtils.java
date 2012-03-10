package com.rozarltd.util.java.lang;

import java.util.List;

public class CollectionUtils {
    public <T> void addIfNotNull(T item, List<T> list) {
        if(list != null && item != null) {
            list.add(item);
        }
    }
}
