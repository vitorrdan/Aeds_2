package Registros.TADS;
public class FilaFlex {
    private Celula primeiro, ultimo;

    public FilaFlex(){ //Construtor da Fila
        primeiro = ultimo = new Celula();
    }
    /*
     * Principio FIFO primeiro a entrar na fila e o primeiro a sair, ultimo a entrar e o ultimo a sair
     * Metodos de inserirFim e removerInicio ou inserirInicio e removerFim
     */

    void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    int removerInicio()throws  Exception{
        if(primeiro == ultimo){
            throw new Exception("Fila vazia");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int resu = primeiro.elemento;
        tmp.prox = null; //desconceta a celula do encadeamento
        tmp = null;
        return resu;
    }

    void mostrar(){
        System.out.print("[ ");
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento + " ");
        }
        System.out.print(" ]");
    }

    public static void main(String[] args)throws Exception {
        FilaFlex fila = new FilaFlex();
        fila.inserirFim(19);
        fila.inserirFim(8);
        fila.inserirFim(787);
        fila.removerInicio();
        fila.mostrar();
        
    }

}





