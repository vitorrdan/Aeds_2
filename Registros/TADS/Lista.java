package Registros.TADS;

public class Lista {
    
    int[] array;
    int N;

    /*
     * Construtores
     */

    public Lista(){
        this(5);
    }

    public Lista(int n){
        array = new int[n];
        N = 0;
    }

    /*
     * Metodos fundamentais
     */

    void InserirInicio(int x)throws Exception{
        if(N >= array.length){ //retorna a quantidade de elementos do array no momento
            throw new Exception("Erro: sem espaço");
        }

        //Remanejar elementos para direita
        for(int i = N; i > 0; i--){
            array[i] = array[i - 1];
        }

        array[0] = x;

        N++; //Incrementa a quantidade de elementos do array
    }


    void InserirFim(int x)throws Exception{
        if(N >= array.length){
            throw new Exception("Erro: sem espaço");
        }

        //Atribui valor passado na posicao N do array
        array[N] = x;
        N++;
    }

    void InserirPos(int x, int pos) throws Exception{
        if(N >= array.length || pos < 0 || pos > N){
            throw new Exception("Erro: falha ao inserir");
        }

        for(int i = N; i > pos; i--){
            array[i] = array[i - 1];
        }

        //Insere elemento na pos dada
        array[pos] = x;
        N++;

    }

    void Inserir(int x)throws Exception{
        if(N >= array.length){
            throw new Exception("Erro: falha ao inserir");
        }

        int pos;
        for(pos = N - 1; pos >= 0 && array[pos] > x; pos--){
            array[pos + 1] = array[pos];
        }

        array[pos + 1] = x;
        N++;
    }
 
    int RemoverInicio()throws Exception{
        if(N == 0){
            throw new Exception("Erro: sem elementos na lista");
        }

        int resp = array[0]; //Armazena elemento removido logicamente em resp
        N--; //Decrementa o numero de elementos da lista

        for(int i = 0; i < N; i++){
            array[i] = array[i + 1]; //Arruma os elementos da Lista apos remoçao do primeiro
        }

        return resp;
    }

    int RemoverFim()throws Exception{
        if(N == 0){
            throw new Exception("Erro: sem elementos na Lista");
        }

        return array[--N]; //Remove elemento 
    }

    int RemoverPos(int pos)throws Exception{
        if(N == 0 || pos < 0 || pos > N){ throw new Exception("Erro: falha ao remover");}

        int resp = array[pos];
        N--;

        for(int i = pos; i < N; i++){
            array[i] = array[i + 1];
        }

        return resp;

    }

    void Mostrar(){
        System.out.print("[");
        for(int i = 0; i < N; i++){
            System.out.print(array[i] + " ");
        }
        System.out.print("]");
    }

     public static void main(String[] args)throws Exception{

        Lista lista = new Lista(5);
       lista.Inserir(13);
       lista.Inserir(12);
       lista.Inserir(5);
       lista.Inserir(3);
       lista.Inserir(5);
        lista.Mostrar();
        /*lista.RemoverFim();
        lista.RemoverInicio();
        lista.Mostrar();*/




     }

}
