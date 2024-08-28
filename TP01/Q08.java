import java.io.*;

public class Q08 {

    public static void main(String[] args){
         
        // Instancia obj do tipo RandomAcessFile e cria um arq para ler e escrever
        RandomAccessFile arq = new RandomAccessFile("exercicio8.txt", "rw");
        int N = MyIO.readInt();
         
        //grava os N numeros reais no arquivo
        for(int i = 0; i < N; i++){
            arq.writeDouble(MyIO.readDouble());

        }
        
        //fecha o arquivo
        arq.close();

        //Abre o  arq denovo apenas para leitura de dados
        arq = new RandomAccessFile("exercicio8.txt", "r");

        //Atribui o tamanho total do arquivo em tam
        long tam = arq.length();
        arq.seek(tam);

        //valor inicial
        long atual = tam - 8;

        //enquanto o valor atual for maior ou igual a zero imprime o valor armazenado na posicao x do arq 
        while (atual >= 0) {
            arq.seek(atual);
            double numero = arq.readDouble();
             if(numero == Math.floor(numero)){
                System.out.println((int) numero);
             } else{
                System.out.println(numero);
             }
            
            atual -= 8; // decrementando 8 bytes para p proximo double
        }

       arq.close();
    
        

    }
    
}
