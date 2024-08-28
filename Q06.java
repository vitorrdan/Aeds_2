import java.util.*;

public class Q06 {

    /*
     * Metodos que verificam se string passada como parametro é
     * apenas constiuidas de consoantes,vogais,inteiros e reais
     */
    
    static boolean isVogal(String palavra){
        boolean resp = true; 

        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if(!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
               || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')){
                 resp = false;
             }
        }
        return resp;
    }

    static boolean isConsoante(String palavra){
        boolean resp = true;

        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if(!(Character.isLetter(c) && !isVogal(Character.toString(c)))){
                resp = false;
            }
        }
        return resp;
    }

    static boolean isInteiro(String palavra){
        boolean resp = true;
        for(int i = 0; i < palavra.length(); i++){
            if(palavra.charAt(i) < '0' || palavra.charAt(i) > '9'){
                resp = false;
            }
        }
        return resp;
    }

    static boolean isReal(String palavra){
        boolean pontoEncontrado = false;

        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if(c == '.' || c == ','){
                if(pontoEncontrado) {
                    return false; 
                }
                pontoEncontrado = true;
            } else if(c < '0' || c > '9'){
                return false;
            }
        }

        return pontoEncontrado;
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        String entrada;
        while(true){
            entrada = sc.nextLine();

            
        if (entrada.equals("FIM")) {
            break;
        }

        if (isVogal(entrada)) {
            System.out.print("SIM ");
        } else {
            System.out.print("NAO ");
        }

        if (isConsoante(entrada)) {
            System.out.print("SIM ");
        } else {
            System.out.print("NAO ");
        }

        if (isInteiro(entrada)) {
            System.out.print("SIM ");
        } else {
            System.out.print("NAO ");
        }

        if (isReal(entrada)) {
            System.out.println("SIM ");
        } else {
            System.out.println("NAO ");
        }

    
           
        }

     sc.close();

    }
}
