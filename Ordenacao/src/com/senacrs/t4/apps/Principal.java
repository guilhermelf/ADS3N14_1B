package com.senacrs.t4.apps;

import com.senacrs.t4.sort.BubbleSort;
import com.senacrs.t4.sort.HeapSort;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author fraga
 */
public class Principal {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Random r = new Random();
        int tamanho = 0;
        
        System.out.printf("Ordenaco de vetores (BubbleSort e HeapSort)\nDigite o tamanho do vetor que deseja ordenar: ");
        try {
            tamanho = Integer.parseInt(leitor.nextLine());
        } catch (Exception e) {
            System.out.println("Valor invalido.");
        }
        
        Integer[] v = new Integer[tamanho];
        for (int i = 0; i < v.length; i++) {
           v[i] = r.nextInt(1001);         
        }
       
        if(v.length < 1001) {
        	System.out.println("\nImpressao do valor na ordem criada:");
        	imprimirArray(v);
        }
        
        ordenarBubble(v);
        ordenarHeap(v);
    }
    
    public static void ordenarBubble(Integer[] v) {
        long[] estatisticasBubble = {0,0};
       
        System.out.println("\nBubbleSort:"); 
        Integer[] bubble = v; 
        estatisticasBubble = BubbleSort.sortWithStatus(bubble);
        if(v.length < 1001)
        	imprimirArray(bubble);
        imprimirEstatisticas(estatisticasBubble);
    }
    
     public static void ordenarHeap(Integer[] v) {
        long[] estatisticasHeap = {0,0};
               
        System.out.println("\nHeapSort:");
        Integer[] heap = v;
        estatisticasHeap = HeapSort.sort(heap);
        if(v.length < 1001)
        	imprimirArray(heap);
        imprimirEstatisticas(estatisticasHeap);
    }
    
    public static void imprimirArray(Integer[] v) {
        for (int i = 0; i < v.length; i++) {
            if(i == v.length - 1)
                System.out.printf("%d}\n", v[i]);
            else if(i == 0)
                System.out.printf("{%d, ", v[i]);
            else 
                System.out.printf("%d, ", v[i]);
        }
    }
    
    public static void imprimirEstatisticas(long[] estatisticas) {
        System.out.printf("Numero de comparacoes: %d\nNumero de trocas: %d\nTempo: %d ms\n", estatisticas[0], estatisticas[1], estatisticas[2]);
    }
}
