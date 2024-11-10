package Registros.TADS;

public class Celula {
    public int elemento;
    public Celula prox;

    public Celula(int x){
        this.elemento = x;
        this.prox = null;
    }

    public Celula(){
        this(0);
    }
    
}
