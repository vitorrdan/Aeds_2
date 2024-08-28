import java.util.*;

public class ciframento {

    /*
     * Metodo para deslocamento de caracteres, armazena o char da posicao i em caractere e depois adiciona no novo vetor de char criptografado adicionado 3 posicoes
     * Retorna o vetor de char criptografado 
     */
    public static String cifrar(String palavra){
        char criptograda[] = new char[palavra.length()];
        for(int i = 0; i < palavra.length(); i++){
            char caractere = palavra.charAt(i);
            criptograda[i] = (char)(caractere + 3);
        }

        return new String(criptograda);
    }

    /*
     * Metodo para detectar palavra "FIM" e encerrar o programa
     */

     public static boolean isFIM(String palavra){
        return (palavra.length() >= 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M');
     }

    

    public static void main(String[] args){
        

        while(true){
           String palavra = MyIO.readLine();
           
            if(isFIM(palavra)){//Sai do loop casa seja digitado FIM
                break;
            }

            MyIO.println(cifrar(palavra));

        }

        

    }

}