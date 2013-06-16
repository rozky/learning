package com.rozarltd.util.java.lang;

import java.util.Collection;

public class CollectionUtilities {
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
