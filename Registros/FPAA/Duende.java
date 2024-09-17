package Registros.FPAA;
import java.util.*;

public class Duende {

        String nome;
        int idade;


        public Duende(String nome, int idade){
            this.idade = idade;
            this.nome = nome;
        }

        public int getIdade(){
            return idade;

        }

        public void setIdade(int idade){
            this.idade = idade;
        }

        public String getNome(){
            return nome;
        }

        public void setNome(String nome){
            this.nome = nome;
        }

     @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade;
    }


    void Insercao(int[] array, int n){
        for(int i = 1; i < n; i++){
            int tmp = array[i];
            int j = i -1;
             while((j>=0) && (array[j] > tmp)){
                array[j +1] = array[j];
                j--;
             }
             array[j + 1] = tmp;
        }

    }


    void Insercao(String[] array, int n){
        for(int i = 1; i < n; i++){
            String tmp = array[i];
            int j = i - 1;
             while((j >= 0) && (array[j].compareTo(tmp)) > 0){
                array[j + 1] = array[j];
                j--;
             }
             array[j+1] = tmp;

        }
    }


    public static void main(String[] args){
        /*Scanner sc = new Scanner(System.in);
        int N;
        N = sc.nextInt();
        sc.nextLine(); //consumir \n

        ArrayList<Duende> lista = new ArrayList<>();

        for(int i = 0; i < N; i++){
            String nome = sc.nextLine();
            int idade = sc.nextInt();
            sc.nextLine();
            Duende d = new Duende(nome, idade);
            lista.add(d);
            
        }

        lista.sort(new Comparator<Duende>() {
            
            public int compare(Duende d1, Duende d2) {
                return Integer.compare(d1.getIdade(), d2.getIdade());
            }
            
        });

       Collections.reverse(lista);*/

       int[] array = {9,7,5,4,66,77,5};
       Arrays.sort(array);
       for(int i = 0; i< 7; i++){
        System.out.println(array[i] + " ");
       }



       


        

    }
}
