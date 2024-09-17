package Registros.TADS;
import java.util.*;

public class ExemplosCollections {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
    
        /*
         * Exercicio 1
         */
        ArrayList<Integer> Notas = new ArrayList<>();
        int soma = 0;

        for(int i = 0; i < 5; i++){
            int num = sc.nextInt();
            Notas.add(num);
            soma+=num;
        }

        float media = soma / 5;
        System.out.println("Media: " + media);

        for (Integer i : Notas) {
            if(i > media){
                System.out.println(i);
            }
            
        }


        sc.close();

    

    }


}
    
