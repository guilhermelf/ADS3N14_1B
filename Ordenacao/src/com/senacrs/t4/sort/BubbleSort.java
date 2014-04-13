package com.senacrs.t4.sort;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fraga
 */
public class BubbleSort {
     public static <T extends Comparable<? super T>> void sort(T[] v) {
         T temp = null;
        
         for (int i = 0; i < v.length - 1; i++) {
             for (int j = 0; j < v.length - 1; j++) {
                 if(v[j].compareTo(v[j+1]) > 0) {
                     temp = v[j];
                     
                     v[j] = v[j + 1];
                     v[j + 1] =  temp;
                 }             
             }  
         }   
     }

     public static <T extends Comparable<? super T>> long[] sortWithStatus(T[] v) {
         long start = System.currentTimeMillis(); 
    	
         long[] status = {0, 0, 0};
      
         T temp = null;
           
         for (int i = 0; i < v.length - 1; i++) {
             for (int j = 0; j < v.length - 1; j++) {
                 status[0]++;
                 if(v[j].compareTo(v[j+1]) > 0) {
                     temp = v[j];
                     
                     v[j] = v[j + 1];
                     v[j + 1] =  temp;
                     status[1]++;
                 }             
             }  
         }   
         
         long finish = System.currentTimeMillis(); 
         status[2] = finish - start;
         return status;
     }
}
    
