import java.util.*;

public class lab02{

    static String espelharNum(int nume) {
        String num = String.valueOf(nume);
        
        if (num.length() > 1) {
            // Construir a string espelhada manualmente
            StringBuilder espelhado = new StringBuilder();
            for (int i = num.length() - 1; i >= 0; i--) {
                espelhado.append(num.charAt(i));
            }
            return espelhado.toString();
        } else {
            return num;
        }
    }
    
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int count = 0;
        
        while(count < 3){

            int n = sc.nextInt();
            int l = sc.nextInt();

            for(int i = n; i <= l; i++){
                System.out.print(i);
            }

            for(int j = l; j >= n; j--){
                System.out.print(espelharNum(j));
            }
            
            count++;

            System.out.println();


        }

        sc.close();
        
        

        }

        
         
    }


