import java.util.*;

public class lab03{

    static boolean isCorreto(String entrada){
        int count = 0; 

        for (int i = 0; i < entrada.length(); i++) {
            char ch = entrada.charAt(i);
    
            if (ch == '(') {
                count++;
            }
    
            if (ch == ')') {
                count--;
            }
    
            // Se o contador ficar negativo, significa que há um parêntese de fechamento
            // sem um correspondente de abertura
            if (count < 0) {
                return false;
            }
        }
    
        // Se o contador não for zero, significa que há parênteses de abertura não fechados
        return count == 0;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        String entrada;

        while(true){
            entrada = sc.nextLine();
            if(entrada.equals("FIM")){
                break;
            }

            if(isCorreto(entrada)){
                System.out.println("correto");
            } else {
                System.out.println("incorreto");
            }

        }
        sc.close();

    }
}