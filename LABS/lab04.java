import java.util.*;

public class lab04 {

     public static boolean Swap(int a, int b, int M) {
        int modA = a % M;
        int modB = b % M;

        // Ordenar pelo valor do mod
        if (modA != modB) {
            return modA > modB;
        }

        // impares precedem pares
        boolean isA_impar = (a % 2 != 0);
        boolean isB_impar = (b % 2 != 0);

        if (isA_impar && !isB_impar) {
            return false; 
        }
        if (!isA_impar && isB_impar) {
            return true;  // par vem depois de ímpar
        }

        // entre impares maior vem primeiro
        if (isA_impar && isB_impar) {
            return a < b;  
        }

        // entre pares o menor vem primeiro
        if (!isA_impar && !isB_impar) {
            return a > b;  
        }

        return false;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int N, M;

        while(true){
            N = sc.nextInt();
            M = sc.nextInt();

            if( N == 0 && M == 0){
                break;
            }

            int[] array = new int[N];

            for(int i = 0; i < N; i++){
                array[i] = sc.nextInt();
            }

            for (int i = 1; i < N; i++) {
                int tmp = array[i];
                int j = i - 1;

                // Comparar e inserir na posição correta
                while (j >= 0 && Swap(array[j], tmp, M)) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = tmp;
            }

            System.out.printf("%d %d", N, M );
            System.out.println();

            for (int i = 0; i < N; i++) {
                System.out.println(array[i]);
            }

	}
        System.out.println("0 0");

        sc.close();

        }

}

/**
 * Complexidade dos metodos:
 * Funcao Swap: O(1) - Dentro do Insercao: O(N^2)
 * Algoritmo de insercao: O(N^2)
 */
