import java.util.*;

public class Palindromo_Recu {

/*
 * Metodo de start
 */
    public static boolean isPalindromo(char[] palavra) {
        return isPalindromoRecursivo(palavra, 0, palavra.length - 1);
    }

    /*
     * Recursivamente compara os caracteres de inicio e fim de cada interacao, se todos forem iguais equanto inicio nao e maior que fim retorna true
     */
    
    private static boolean isPalindromoRecursivo(char[] palavra, int inicio, int fim) {
        if (inicio >= fim) {
            return true;
        }
    
        if (palavra[inicio] != palavra[fim]) {
            return false;
        }
    
        return isPalindromoRecursivo(palavra, inicio + 1, fim - 1);
    }
    

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){

            String entrada = sc.nextLine();
            char[] vetor = entrada.toCharArray();

            if(entrada.equals("FIM")){
                break;
            }

            if(isPalindromo(vetor)){
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        sc.close();

    }
    
}
