import java.util.*;

public class Q04 {

    /*
     * Metodo para gerar caracteres aleatorios e troca-los se estiverem na string passada
     */

    static String Alteracao(String palavra){

        Random gerador = new Random();
        gerador.setSeed(4);
        char c1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char c2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

        StringBuilder novaPalavra = new StringBuilder(palavra);

        for(int i = 0; i < novaPalavra.length(); i++){
            if(novaPalavra.charAt(i) == c1){
                novaPalavra.setCharAt(i, c2);
            }
            

        }

        return novaPalavra.toString();

    }


    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String entrada;
        /*
         * Encerra programa quando 'FIM' for digitado
         */

        while(true){
            entrada = sc.nextLine();
            if(entrada.equals("FIM")){
                break;
            }

            System.out.println(Alteracao(entrada));
            

        }
       
        sc.close();
    }
    
}
