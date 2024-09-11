package Registros.TADS;

public class FilaCircular {

    int[] array;
    int primeiro, ultimo;

    FilaCircular(int tam){
        array = new int[tam];
        primeiro = ultimo = 0;

    }

    void inserir(int x)throws Exception{
        if(((ultimo + 1) % array.length) == primeiro){
            throw new Exception("Erro lista cheia");
        }

        array[ultimo] = x; //Inserimos em ultimo em filas
        ultimo = ((ultimo + 1) % array.length); //Anda com indicar ultimo  
    }

    int remover()throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Erro: nao ha elementos na fila");
        }

        int resp = array[primeiro];
        primeiro = (primeiro + 1) % array.length; //Andar com indicar de primeiro;

        return resp;
    }

    void Mostra(){
        System.out.print("[");

        int i = primeiro;
        while(i != ultimo){
            System.out.print(array[i] + " ");
            i = (i + 1) % array.length;
        }

        System.out.print("]");

    }

    void MostrarRec(){
        System.out.print("{");
        MostrarRec(primeiro);
        System.out.print("}");
    }

    void MostrarRec(int i){
        if(i != ultimo){
            System.out.print(array[i] + " ");
            MostrarRec((i+1) % array.length);
        }
    }

    boolean isVazia(){
        return(primeiro == ultimo);
    }


    public static void main(String[] args)throws Exception{
        FilaCircular fila_circ = new FilaCircular(5);
        fila_circ.inserir(13);
        fila_circ.inserir(12);
        fila_circ.inserir(14);
        fila_circ.Mostra();
        fila_circ.MostrarRec();

    }
    
}
