package TP03;
import java.io.*;

public class Matriz{
  //CelulaMatriz Matriz com 4 ponteiros
   class CelulaMatriz {
      public int elemento;
     public CelulaMatriz inf, sup, esq, dir;
  
     public CelulaMatriz(){
        this(0);
     }
  
     public CelulaMatriz(int elemento){
        this(elemento, null, null, null, null);
     }
  
     public CelulaMatriz(int elemento, CelulaMatriz inf, CelulaMatriz sup, CelulaMatriz esq, CelulaMatriz dir){
        this.elemento = elemento;
        this.inf = inf;
        this.sup = sup;
        this.esq = esq;
        this.dir = dir;
     }
  }


public CelulaMatriz inicio;
   private int linha,coluna;

   public Matriz(){
      this(3,3);
   }

   public Matriz(int l, int c){
      this.linha = l;
      this.coluna = c;
   }

   	
   //Metodo para construir e inserir valores na Matriz
   public void Inserir() throws Exception{
      CelulaMatriz nova = new CelulaMatriz();
      CelulaMatriz cima; 
      this.inicio = new CelulaMatriz();
      
      int elemento;
  
      for(int i = 0; i < this.linha; i++){
         cima = inicio;
         for(int k = i; k > 0; k--)
         cima = cima.inf;
          for(int j = 0; j < this.coluna; j++){
            elemento =  MyIO.readInt();

             if((i == 0) && (j ==0)){
               this.inicio.elemento = elemento;
               nova = this.inicio;
             } else {
               nova.dir = new CelulaMatriz(elemento);
               nova.dir.esq = nova;
               nova = nova.dir;

               if(i > 0){
                  cima = inicio;

                  for(int k = i; k > 1; k--)
                  cima = cima.inf;

                  for(int k = j; k > 0; k--)
                  cima = cima.dir;

                  nova.sup = cima;
                  cima.inf = nova;
               }
             }
          }
      }

   }


  //Metodo cria uma matriz resultante com mesmos numeros de linhas e colunas e realiza a soma das matrizes passadas 
  public Matriz Soma(Matriz m){

   Matriz resu = new Matriz(m.linha, m.coluna);
   resu.inicio = new CelulaMatriz();
  //instancias auxiliares para percorrer as matrizes
   CelulaMatriz celula_resu = resu.inicio;
   CelulaMatriz celula_m1 = this.inicio;
   CelulaMatriz celula_m2 = m.inicio;
   CelulaMatriz cima = resu.inicio;

   for(int i = 0; i < linha; i++){
      celula_m1 = this.inicio;
      celula_m2 = m.inicio;
      celula_resu = resu.inicio;

      //Navega para a linha correta se nao for a primeira linha
      if(i > 0){
         for(int k = i; k > 1; k--){
            celula_m1 = celula_m1.inf;
            celula_m2 = celula_m2.inf;
            celula_resu = celula_resu.inf;
         }

         celula_m1 = celula_m1.inf;
         celula_m2 = celula_m2.inf;
         celula_resu.inf = new CelulaMatriz();
         celula_resu = celula_resu.inf;
      }

      //Itera sobre as colunas da matriz
      for(int j = 0; j < coluna; j++){
        //Soma dos elementos
         celula_resu.elemento = celula_m1.elemento + celula_m2.elemento;
            celula_resu.dir = new CelulaMatriz();
				celula_resu.dir.esq =  celula_resu;
				celula_resu = celula_resu.dir;
				celula_m1 = celula_m1.dir;
				celula_m2 = celula_m2.dir;

            if(i > 0){
               cima = resu.inicio;

               for(int k = i; k > 1; k--)
               cima = cima.inf;

               for(int k = j; k >= 0; k--)
               cima = cima.dir;

               celula_resu.sup = cima;
               cima.inf = celula_resu;
            }

      }
   }
   return resu;

  }

  //Metodo realiza a multiplacao de duas matrizes percorrendo as células das matrizes, realizando a multiplicação de elementos correspondentes
  // e armazenando os resultados em uma nova matriz
  public Matriz Multiplicacao(Matriz m) {

   Matriz resu = new Matriz(m.linha, m.coluna);
   resu.inicio = new CelulaMatriz();
   //Intancias para auxiliar para percorrer a matriz
   CelulaMatriz celula_resu = resu.inicio;
   CelulaMatriz celula_m1 = this.inicio;
   CelulaMatriz celula_m2 = m.inicio;
   CelulaMatriz cima = resu.inicio;
   CelulaMatriz aux = celula_m1;
   CelulaMatriz aux2 = celula_m2;
   int soma;

   for (int i = 0; i < linha; i++) {
       celula_m1 = this.inicio;
       celula_m2 = m.inicio;
       celula_resu = resu.inicio;

       if (i > 0) {
           for (int k = i; k > 1; k--) {
               celula_m1 = celula_m1.inf;
               celula_resu = celula_resu.inf;
           }
           celula_m1 = celula_m1.inf;
           celula_m2 = m.inicio;
           celula_resu.inf = new CelulaMatriz();
           celula_resu = celula_resu.inf;
       }

       aux = celula_m1;
       aux2 = celula_m2;

       for (int j = 0; j < coluna; j++) {
           soma = 0;
            // Loop interno para multiplicar os elementos correspondentes das duas matrizes
           for (int k = 0; k < linha; k++) {
               soma += celula_m1.elemento * celula_m2.elemento;
               celula_m1 = celula_m1.dir;
               celula_m2 = celula_m2.inf;
           }

           celula_resu.elemento = soma;
            // Cria a próxima célula na linha da matriz resultado
           celula_resu.dir = new CelulaMatriz();
           celula_resu.dir.esq = celula_resu;
           celula_resu = celula_resu.dir;
           celula_m1 = aux;
           aux2 = aux2.dir;
           celula_m2 = aux2;

           // Ajusta os ponteiros 'sup' e 'inf' para conectar verticalmente as células da matriz resultado
           if (i > 0) {
               cima = resu.inicio;
               for (int k = i; k > 1; k--) cima = cima.inf;
               for (int k = j; k >= 0; k--) cima = cima.dir;

               celula_resu.sup = cima;
               cima.inf = celula_resu;
           }
       }
   }
   return resu;
}

   //Metodo boolean retorna true se Matriz tiver o mesmo numero de linhas e colunas
   public boolean isQuadrada(){
      return (this.linha == this.coluna);
   }


   //Metodo para imprimir Diagonal principal,se a matriz for quadrada
   public void mostrarDiagonalPrincipal() {
      if (isQuadrada()) {
          CelulaMatriz celulaAtual = inicio;
  
          while (celulaAtual != null) {
              System.out.printf("%d ", celulaAtual.elemento);
              if (celulaAtual.inf != null && celulaAtual.inf.dir != null) {
                  celulaAtual = celulaAtual.inf.dir; // Move para a célula diagonal seguinte
              } else {
                  break; //Sai do loop se nn houver mais celulas na diagonal
              }
          }
      } else {
          System.out.println("Erro");
      }
  }

  //Metodo para imprimir diagonal secundaria, se a matriz for quadrada
  public void mostrarDiagonalSecundaria() {
   if (isQuadrada()) {
       CelulaMatriz CelulaAtual = inicio;

       for (int i = 0; i < coluna - 1; i++) {
           if (CelulaAtual.dir != null) {
               CelulaAtual = CelulaAtual.dir;
           }
       }

       // Itera pela diagonal secundária
       while (CelulaAtual != null) {
           System.out.printf("%d ", CelulaAtual.elemento);

           if (CelulaAtual.inf != null && CelulaAtual.inf.esq != null) {
               CelulaAtual = CelulaAtual.inf.esq; 
           } else {
               break; // Sai do loop se nn houver mais celulas na diagonal 
           }
       }
   } else {
       System.out.println("Erro");
   }
}

   //Metodo para imprimir Matriz
   public void Mostrar(){
     
      CelulaMatriz linhaAtual = this.inicio; 
      CelulaMatriz celulaAtual; // Variável para iterar sobre as células em uma linha
  
      for (int i = 0; i < linha; i++) {
          celulaAtual = linhaAtual; 
  
          for (int j = 0; j < coluna; j++) {
              System.out.printf("%d ", celulaAtual.elemento); // Imprime o elemento da celula atual
              celulaAtual = celulaAtual.dir; 
          }
  
          System.out.println(); 
          linhaAtual = linhaAtual.inf; 
      }

   
   }

   public static void main(String[] args) throws Exception{
      
      
      //Quantidade de casos testes
      int n = MyIO.readInt();

      for (int i = 0; i < n; i++) {
         Matriz m1, m2, resu;
 
         // Leitura das dimensões e dos elementos da primeira matriz
         int linhaM1 = MyIO.readInt();
         int colunaM1 = MyIO.readInt();
         m1 = new Matriz(linhaM1, colunaM1);
         m1.Inserir();
 
         // Exibição da diagonal principal e secundária da primeira matriz
         m1.mostrarDiagonalPrincipal();
         System.out.println();
         m1.mostrarDiagonalSecundaria();
 
         // Leitura das dimensões e dos elementos da segunda matriz
         int linhaM2 = MyIO.readInt();
         int colunaM2 = MyIO.readInt();
         m2 = new Matriz(linhaM2, colunaM2);
         m2.Inserir();
 
         // Soma das matrizes e exibição do resultado
         System.out.println();
         resu = m1.Soma(m2);
         resu.Mostrar();
 
         // Multiplicação das matrizes e exibição do resultado
         resu = m1.Multiplicacao(m2);
         resu.Mostrar();
     }

   }

}


