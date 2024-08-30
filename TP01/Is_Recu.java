import java.util.*;

public class Is_Recu {
    /*
     * Metodos que verificam se string passada como parametro é
     * apenas constiuidas de consoantes,vogais,inteiros e reais
     */

      // Função auxiliar para iniciar a recursão
    static boolean isVogal(String palavra) {
        return isVogal(palavra, 0);
    }


     static boolean isVogal(String palavra, int i) {
        if (i == palavra.length()) {
            return true;
        }
    
        char c = palavra.charAt(i);
        boolean ehVogal = (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                           c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U');
    
        if (!ehVogal) {
            return false;
        }
    
        return isVogal(palavra, i + 1);
    }


    //Função auxiliar para iniciar a recursão
    static boolean isConsoante(String palavra){
        return isConsoante(palavra,0);
    }

    static boolean isConsoante(String palavra, int i){
        if(i == palavra.length()){
            return true;
        }

        char c = palavra.charAt(i);
        boolean ehConsoante = !isVogal(Character.toString(c));

        if(!ehConsoante || Character.isDigit(c)){
            return false;
        }

        return isConsoante(palavra, i + 1);
    }
    
   
    
    /*static boolean isConsoante(String palavra){
        boolean resp = true;

        for(int i = 0; i < palavra.length(); i++){
            char c = palavra.charAt(i);
            if(!(Character.isLetter(c) && !isVogal(Character.toString(c)))){
                resp = false;
            }
        }
        return resp;
    }*/


    //Função auxiliar para iniciar a recursão
    static boolean isInteiro(String palavra) {
        return isInteiro(palavra, 0);
    }
    
    static boolean isInteiro(String palavra, int i) {
        
        if (i == palavra.length()) {
            return true;
        }
    
        char c = palavra.charAt(i);
        if (c < '0' || c > '9') {
            return false;
        }
    
        return isInteiro(palavra, i + 1);
    }



    //Função auxiliar para iniciar a recursão
    static boolean isReal(String palavra) {
        return isReal(palavra, 0, false);
    }
    
    static boolean isReal(String palavra, int i, boolean pontoEncontrado) {
        if (i == palavra.length()) {
            return pontoEncontrado;
        }
    
        char c = palavra.charAt(i);
    
        if (c == '.' || c == ',') {
            if (pontoEncontrado) {
                return false;
            }

            return isReal(palavra, i + 1, true);

        } else if (c < '0' || c > '9') {
            return false; 
        }
    
        return isReal(palavra, i + 1, pontoEncontrado);
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
