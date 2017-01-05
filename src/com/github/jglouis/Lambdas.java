package com.github.jglouis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Example of lambda functions
 */
public class Lambdas {

    private static final int MAX_THREADS = 5;
    private static final int NUMBER_OF_TASKS = 100;

    public static void main (String[] args){
        List<String> names = Arrays.asList("Bob", "Peter", "Alice", "Robert");

        names.sort((a, b) -> a.compareTo(b));

        System.out.println(names);

        ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {
            int finalI = i;
            callables.add(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("task " + finalI + " has been executed in thread " + Thread.currentThread().getName());
                return finalI;
            });
        }
        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                        return null;
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
