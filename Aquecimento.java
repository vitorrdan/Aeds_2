import java.util.*;

public class Aquecimento {

    /*
     * Metodo para detectar letras Maisculas
     */
    public static boolean isMaiuscula(char c){
        return(c >= 'A' && c <= 'Z');
    }

    /*
     * Metodo para contar letras Maisculas recursivamente
     */

    static int count_M(String palavra){
        return count_M(palavra, 0, 0);
    }
    static int count_M(String palavra, int i, int count){
        if(i >= palavra.length()){
            return count;
        }
        if(isMaiuscula(palavra.charAt(i))){
            count++;

        }
       
        return count_M(palavra, i+1, count) ;
    }


   
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);

       String entrada;

       while(true){
        entrada = sc.nextLine();
        
        if(entrada.equals("FIM")){
            break;
        }

        System.out.println(count_M(entrada));

       }

       sc.close();


    }
    


}