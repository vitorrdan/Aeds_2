import java.util.*;

public class lab02{
   
   

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        for(int x = 0; x < 3; x++){

            int n = sc.nextInt();
            int l = sc.nextInt();

            for(int i = n; i <= l; i++){
                System.out.print(i);
            }

            for(int j = l; j >= n; j--){
                System.out.print(j);
            }

            System.out.println();


        }

        sc.close();

        }

        
         
    }


