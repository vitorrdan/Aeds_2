import java.util.Scanner;

public class Palindromo{

    public static void main(String[] args){
          Scanner scanner = new Scanner(System.in);

          while (true) {
           
            String input = scanner.nextLine();
            char[] meuVetor = input.toCharArray();
                
            if (isFIM(meuVetor)) 
                break; // Sai do loop se FIM' for digitado
                 
            if (isPalindromo(meuVetor)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
        }

        scanner.close();
    }


    // metodo para identificar se uma string é um palindromo, retorna true se sim
    //Primeiro identifica se é "FIM" depois cria uma copia da string recebida que sera invertida depois comparada com a original
    //se for igual retorne true
    public static boolean isPalindromo(char[] palavra){ 
        
        char[] copia;
        copia = new char[palavra.length];
        
        for (int i = 0; i < palavra.length ; i++){
            copia[i] = palavra[palavra.length - 1 - i];
            
        }
        
        for(int i = 0; i < palavra.length; i++){
            if(copia[i] != palavra[i]){
                return false;
            }
           
        }
        return true;
        
    }

    //Identificador da palavra 'fim'
    public static boolean isFIM(char[] s){  
        return (s.length >= 3 && s[0] == 'F' && s[1] == 'I' && s[2] == 'M');
    }


}