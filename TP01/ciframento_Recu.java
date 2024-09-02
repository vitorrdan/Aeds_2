public class ciframento_Recu {

    /*
     * Metodo cifrar porem recursivo
     */

    public static char[] criframento(char[] palavra){
        return criframento(palavra, 0);
    }

    private static char[] criframento(char[] palavra, int i){
        if(i == palavra.length){
            return palavra;
        } else {
            palavra[i] = (char)(palavra[i] + 3);
            return criframento(palavra, i + 1);
        }


    }

    /*
     * Metodo para detectar palavra "FIM" e encerrar o programa
     */

     public static boolean isFIM(String palavra){
        return (palavra.length() >= 3 && palavra.charAt(0) == 'F' && palavra.charAt(1) == 'I' && palavra.charAt(2) == 'M');
     }

    

    public static void main(String[] args){
        

        while(true){
        String palavra = MyIO.readLine(); // Lê a entrada do usuário
    
        if (isFIM(palavra)) { // Sai do loop caso seja digitado "FIM"
               break;
           }

        //Converte string recebida para array de char
         char[] palavraArray = palavra.toCharArray();

        //Retorno do metodo ciframento
        char[] palavraCifrada = criframento(palavraArray);

        MyIO.println(new String(palavraCifrada));

        }
    }
    
}
