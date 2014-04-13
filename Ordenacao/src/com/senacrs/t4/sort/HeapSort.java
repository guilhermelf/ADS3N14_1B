package com.senacrs.t4.sort;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fraga
 */
public class HeapSort {
     private static long[] stats = {0, 0, 0}; 
     public static <T extends Comparable<? super T>> long[] sort(T[] v) {
        long start = System.currentTimeMillis(); 
         
        buildMaxHeap(v);
        int n = v.length;
 
        for (int i = v.length - 1; i > 0; i--) {
            swap(v, i, 0);
            maxHeapify(v, 0, --n);
        }
        
        long finish = System.currentTimeMillis(); 
        
        stats[2] = finish - start;
        return stats;
    }
 
    private static <T extends Comparable<? super T>> void buildMaxHeap(T v[]) {
        for (int i = v.length / 2 - 1; i >= 0; i--)
            maxHeapify(v, i, v.length);
    }
 
    private static <T extends Comparable<? super T>> void maxHeapify(T[] v, int pos, int n) {
        int max = 2 * pos + 1;
        int right = max + 1;
        
        stats[0]++;
        if (max < n) {
            if (right < n && v[max].compareTo(v[right]) < 0)
                max = right;
            
            stats[0]++;
            if (v[max].compareTo(v[pos]) > 0) {
                swap(v, max, pos);
                maxHeapify(v, max, n);
            }
        }
    }
 
    public static void swap(Object[] v, int j, int aposJ) {
        Object aux = v[j];
        v[j] = v[aposJ];
        v[aposJ] = aux;
        stats[1]++;
    }   
}


