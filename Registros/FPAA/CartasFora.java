package Registros.FPAA;
import java.util.*;
public class CartasFora {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int n = sc.nextInt();
            if(n == 0){
                break;
            }

            Stack<Integer> pilha = new Stack<>();
            for(int i = n; i > 0; i--){
                pilha.push(i);           //CONSTRUCAO PILHA COM  1 NO TOPO E N BASE
            }

            List<Integer> descartadas = new ArrayList<>();

             while(pilha.size() >= 2){
                descartadas.add(pilha.pop());

                pilha.add(pilha.remove(0));

             }

             int ultimaCarta = pilha.pop(); //ultima carta remanecente

              // Imprimir as cartas descartadas
            System.out.print("Cartas descartadas: ");
            for (int i = 0; i < descartadas.size(); i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(descartadas.get(i));
            }
            System.out.println();

            // Imprimir a última carta remanescente
            System.out.println("Última carta: " + ultimaCarta);

        }


       sc.close();

    }   
    
}
