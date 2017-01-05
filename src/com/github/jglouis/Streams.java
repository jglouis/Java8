package com.github.jglouis;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Example of java8 Streams
 */
public class Streams {

    private static final int MAX_ELEMENT = 1000000;

    public static void main(String[] args) {
	    List<String> values = new ArrayList<>(MAX_ELEMENT);
	    for (int i = 0; i < MAX_ELEMENT; i++){
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // sequential sort
        long t0 = System.nanoTime();
        values.stream().sorted().count();
        long t1 = System.nanoTime();
        long diff = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", diff));

        // parallel sort
        t0 = System.nanoTime();
        values.parallelStream().sorted().count();
        t1 = System.nanoTime();
        diff = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", diff));
    }
}
