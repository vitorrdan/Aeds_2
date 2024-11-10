package Registros.TADS;

public class ListaFlex {
    private Celula primeiro, ultimo;

    public ListaFlex(){
        primeiro = ultimo = new Celula();
    }

    public void inserirInicio(int x){
        Celula tmp = new Celula(x);//instancia nova celula tmp
        tmp.prox = primeiro.prox;//conecta ela ao primeiro elemento atual
        primeiro.prox = tmp;//conecta primeiro.prox para o tmp tornando-o primeiro elemento da lista
        if(primeiro == ultimo){ //se lista estiver vazia atualiza ponteiro de ultimo;
            ultimo = tmp;
        }

    }

    public void inserirFim(int x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    public void inserirPos(int x, int pos)throws Exception{
        int tam = tamanho();
        if(pos < 0 || pos > tam){
            throw new Exception();
        } else if(pos == 0){
            inserirInicio(x);
        } else if(pos == tam){
            inserirFim(x);
        } else {
            Celula i = primeiro;// ja seta tudo antes do for
            for(int j = 0; j < pos; j++, i = i.prox);
            Celula tmp = new Celula(x);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;


            
        }
    }

    public int RemoverFim()throws Exception{//Nao temos referencia para a ultima celula, apenas para onde essa celula aponta, e como el e  a ULTIMA ela aponta pra null ne amore, temos q percorrer a lista ate a celula anterior a ultima para desconctamos ela da lista
       if(primeiro == ultimo){
        throw new Exception("Lista vazia");
       } 
        int resp;
        Celula i;
        for(i = primeiro.prox; i.prox != ultimo; i = i.prox);
        resp = ultimo.elemento;
        ultimo = i;
        i = ultimo.prox = null;

        return resp;
    }

    public int removerInicio()throws Exception{
        if(primeiro == ultimo){
            throw new Exception("Lista vazia");
        }
        Celula tmp = primeiro;
        primeiro = primeiro.prox;
        int resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;

        return resp;
    }

    public int removerInicio_2(){// nao remove o no cabeca original
        if(primeiro == ultimo){
            System.out.println("ERRO LISTA VAZIA");
        }
        Celula tmp = primeiro.prox;
        primeiro.prox = primeiro.prox.prox;
        int resp = tmp.elemento;
        tmp.prox = null;
        tmp = null;

        return resp;
    }

    public void mostrar(){
        System.out.print("[ ");
            for(Celula i = primeiro.prox; i != null; i = i.prox){
                System.out.print(i.elemento + " ");
            }

        System.out.print(" ]");
    }

    public boolean isVazia(){
        boolean resp = false;
        if(primeiro == ultimo){
            resp = true;
        }
        return resp;
    }

    public int tamanho(){
        int count = 0;
        for(Celula i = primeiro.prox; i!= null; i = i.prox, count++);

        return count;
    }


    public static void main(String[] args)throws Exception{
        
    }
    
}
