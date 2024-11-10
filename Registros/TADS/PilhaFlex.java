package Registros.TADS;

public class PilhaFlex {
    private Celula topo;

    public PilhaFlex(){
        topo = null;
    }

    /*
     * LIFO ultimo a entrar primeiro a sair metodos de insercao e remocao ao final, topo aponta pro ultimo elemento inserido e base aponta pra null
     */

    public void inserir(int x){
        Celula tmp = new Celula(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;

    } 

    int remover()throws Exception{
        if(topo == null){
            throw new Exception("ERRO PILHA VAZIA");
        }
        int resp = topo.elemento;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;

        return resp;
    }

    public void mostrar() {
		System.out.print("[ ");
		for (Celula i = topo; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] ");
	}
    
}
