package com.demo;

import java.lang.management.ManagementFactory;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.IntConsumer;

public class CollectionsPerformance {

	
    public static void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static long getCurrentlyUsedMemory() {
        return
            ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() +
            ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
    }
    
    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }

    public static int numIterations = 100_000;

    public static void runTest(IntConsumer f, String name) {
        sleep();
        long start = System.currentTimeMillis();
        long startMem = getCurrentlyUsedMemory();
        f.accept(numIterations);
        System.out.println(name + ": " + (System.currentTimeMillis() - start));
        System.out.println(name + "Mem: " + formatSize(getCurrentlyUsedMemory() - startMem));
    }
    
    public static void main(String[] argv) {
    	//Iterable<T>
    	//Collection<E>
    	//AbstractMap<K, V>
        runTest((v) -> {
            List<Integer> arrayList = new ArrayList<>();
            for(int i=0; i<v; i++) {
                arrayList.add(i);
            }
        }, "ArrayList");
        runTest((v) -> {
            List<Integer> linkedList = new LinkedList<>();
            for(int i=0; i<v; i++) {
                linkedList.add(i);
            }
        }, "LinkedList");

        runTest((v) -> {
            Map<Integer, Integer> hashMap = new HashMap<>();
            for(int i=0; i<v; i++) {
                hashMap.put(i, i);
            }
        }, "HashMap");

        runTest((v) -> {
            Map<Integer, Integer> treeMap = new TreeMap<>();
            for(int i=0; i<v; i++) {
                treeMap.put(i, i);
            }
        }, "TreeMap");


        runTest((v) -> {
            Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
            for(int i=0; i<v; i++) {
                linkedHashMap.put(i, i);
            }
        }, "LinkedHashMap");

        runTest((v) -> {
            Set<Integer> hashSet = new HashSet<>();
            for(int i=0; i<v; i++) {
                hashSet.add(i);
            }
        }, "HashSet");

        runTest((v) -> {
            Set<Integer> linkedHashSet = new LinkedHashSet<>();
            for(int i=0; i<v; i++) {
                linkedHashSet.add(i);
            }
        }, "LinkedHashSet");

        runTest((v) -> {
            Set<Integer> treeSet = new TreeSet<>();
            for(int i=0; i<v; i++) {
                treeSet.add(i);
            }
        }, "TreeSet");

        runTest((v) -> {
            Deque<Integer> arrayDeque = new ArrayDeque<>();
            for(int i=0; i<v; i++) {
                arrayDeque.add(i);
            }
        }, "ArrayDeque");

        runTest((v) -> {
            Queue<Integer> priorityQueue = new PriorityQueue<>();
            for(int i=0; i<v; i++) {
                priorityQueue.add(i);
            }
        }, "PriorityQueue");
    }
}
