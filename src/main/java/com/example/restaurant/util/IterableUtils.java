package com.example.restaurant.util;

import java.util.stream.StreamSupport;

public class IterableUtils {

    public static Boolean isNotEmpty(Iterable iterable) {
        if (iterable == null) {
            return false;
        } else if (StreamSupport.stream(iterable.spliterator(), false).count() == 0) {
            return false;
        }
        return true;
    }
}
