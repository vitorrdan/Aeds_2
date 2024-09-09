package Registros.TADS;

public class Lista {
    
    int[] array;
    int N;

    public Lista(){
        this(5);

    }

    public Lista(int n){
        array = new int[n];
        N = 0;
    }
}
