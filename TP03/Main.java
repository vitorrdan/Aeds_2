import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

 public class Main {

    public static  Pokemon[] removidos = new Pokemon[10];
    public static  int count_removidos = 0;

    class Celula {
        /*
         * Atributos
         */
        private Pokemon elemento;
        private Celula prox;

        public Celula(Pokemon p){
            this.elemento = p;
            this.prox = null;
        }
    }

    /*
     * Classe pilha flexivel
     */
    class Pilha {

        Celula topo;

        public Pilha(){
            this.topo = null;
        }

        public void empilhar(Pokemon p){
            Celula tmp = new Celula(p);//Cria nova celula
            tmp.prox = topo;//celula aponta pro topo
            topo = tmp;//topo aponta pra essa nova celula
            tmp = null;
        }

       public Pokemon desempilhar()throws Exception{
        if(topo == null){
            throw new Exception("Pilha vazia!");
        }
        Pokemon resp = topo.elemento;
        removidos[count_removidos] = resp;
        count_removidos++;
        Celula tmp = topo; //sentinela que fica no topo
        topo = topo.prox;//ponteiro de topo avanca uma celula
        tmp = null;//tmp aponta para null desconectando celula da pilha

        return resp;

        }

        public int tamanho(){
            int count = 0;
            Celula i;
            for(i = topo; i != null; i = i.prox){
                count++;
            };

            return count;



        }

        public void Mostrar(){
            int n = tamanho();
            Pokemon[] pokemons = new Pokemon[n + 1];
            Celula i;
            int j;
    
            for(i = topo ,j = 0; i != null; i = i.prox,j++){
                pokemons[j] = i.elemento;
                
            }

            for(int z = n; z > 0; z--){
                System.out.println("[" + z + "] [" + "#" + pokemons[z].id + " -> " 
                + pokemons[z].name + ": " 
                + pokemons[z].description + " - [" 
                + pokemons[z].types + "] - [" 
                + pokemons[z].abilities + "] - " 
                + pokemons[z].weight_kg + "kg - " 
                + pokemons[z].height_m + "m - " 
                + pokemons[z].captureRate + "% - " 
                + (pokemons[z].isLegendary ? "Legendary" : "Not Legendary") + " - " 
                + pokemons[z].generation + " gen] - " 
                + pokemons[z].capture_date);
                

            }
        }

    }





    class Lista{
    /*
     * Atributos
     */
    private Pokemon[] array;
    private int n;
   


    public Lista(){
        this(6);
    }

    public Lista(int tam){
        array = new Pokemon[tam];
       
        n = 0;
     
    }

    /*
     * Metodos Inserir(Inicio, fim, pos)
     */

    void inserirInicio(Pokemon p)throws Exception{
        if(n >= array.length){
            throw new Exception("Erro lista cheia !");
        }

        for(int i = n; i > 0; i--){
            array[i] = array[i - 1];
        }
        array[0] = p;
        n++;
    } 

    void InserirFim(Pokemon p)throws Exception{
        if(n >= array.length){
            throw new Exception("Lista cheia !");
        }

        array[n] = p;
        n++;
    }

    void inserirPos(Pokemon p, int pos)throws Exception{
        if(n >= array.length || pos < 0 || pos > n ){
            throw new Exception("Lista cheia ou pos invalida !");
        }

        for(int i = n; i > pos; i--){
            array[i] = array[i - 1];
        }

        array[pos] = p;
        n++;
    }

    /*
     * Metodos Remover(Inicio, fim, pos)
     */

    Pokemon removerInicio()throws Exception{
        if(n == 0){
            throw new Exception("Lista vazia");
        }

        Pokemon resp = array[0];
        n--;

        for(int i = 0; i < n; i++){
            array[i] = array[i + 1];
        }

        


        return resp;
     }

    Pokemon removerFim()throws Exception{
        if(n == 0){
            throw new Exception("Lista vazia");
        }
        
        Pokemon resp = array[n - 1];
       

        return array[--n];
     }

    
     Pokemon removerPos(int pos)throws Exception{
        if(n == 0 || pos < 0 || pos > n){
            throw new Exception("Lista vazia ou pos invalido");
        }

        Pokemon resp = array[pos];
        n--;

        for(int i = pos; i < n; i++){
            array[i] = array[i+1];
        }



        return resp;
     } 

     /*
     * Metodo leitura do arquivo CSV e salvamento de pokemons em uma lista
     */

    public void LeituraArquivo(String caminho){
    
    try {
        Scanner sc = new Scanner(new File(caminho));
        sc.nextLine(); // Pular cabeçalho

        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            
            // Aqui consideramos que o CSV pode ter vírgulas dentro de strings, por isso usamos regex.
            String[] infos = linha.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            Pokemon pokemon = new Pokemon();
            pokemon.id = Integer.valueOf(infos[0].trim());
            pokemon.generation = Integer.valueOf(infos[1].trim());
            pokemon.name = infos[2].trim();
            pokemon.description = infos[3].trim();

            // Processar tipos
            ArrayList<String> tiposList = new ArrayList<>();
            tiposList.add(infos[4].trim()); // type1
            if (!infos[5].trim().isEmpty()) {
                tiposList.add(infos[5].trim()); // type2 se existir
            }
            pokemon.setTypes(tiposList);

            // Processar habilidades removendo colchetes e aspas simples
            if (!infos[6].trim().isEmpty()) {
                String[] habilidades = infos[6].replaceAll("[\\[\\]\"]", "").split(",");
                for (String habilidade : habilidades) {
                    pokemon.abilities.add(habilidade.trim()); // Adicionar habilidades
                }
            }

            // Processar peso e altura com tratamento de erros
            if (!infos[7].trim().isEmpty()) {
                pokemon.weight_kg = Double.parseDouble(infos[7].trim());
            }
            if (!infos[8].trim().isEmpty()) {
                pokemon.height_m = Double.parseDouble(infos[8].trim());
            }

            // Processar capture rate e isLegendary
            pokemon.captureRate = Integer.parseInt(infos[9].trim());
            pokemon.isLegendary = Integer.parseInt(infos[10].trim()) == 1;

            // Processar data de captura
            pokemon.capture_date = infos[11].trim();

            InserirFim(pokemon);
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Erro ao processar o arquivo: " + e.getMessage());
    }

    
 }


 /*
  * Metodo para impressao
  */
 public void Mostrar(){

    for(int i = 0; i < n; i++){
        System.out.println("["+i+"] [" + "#" + array[i].id + " -> " + array[i].name + ": " + array[i].description + " - " + array[i].getTypes() + " - " + array[i].getAbilities() + " - " + array[i].weight_kg + "kg" + " - " + array[i].height_m  + "m"+ " - " + array[i].captureRate + "%" + " - " + array[i].isLegendary + " - " + array[i].generation + " gen" + "]" + " - " + array[i].capture_date);

    }
 }

 /*
  * Metodo que retorna pokemon dado id
  */
  public Pokemon PokemonID(int id){
        
    Pokemon encontrado = null;
    
    for (int i = 0; i < 801.; i++) {
        Pokemon p = array[i];
        if (p.getId() == id) {
            encontrado = array[i];
            break;
             
        }
    }
    return encontrado;
    
 }

}
    
     
    class Pokemon {

    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private List<String> abilities;
    private double weight_kg;
    private double height_m;
    private int captureRate;
    private boolean isLegendary;
    private String capture_date;

    /*
     * Classes 
     */

    public Pokemon(int id, int generation, String name, String description, ArrayList<String> types, List<String> abilities, double weight_kg, double height_m, int capture, boolean isLegendary, String data){
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types;
        this.abilities = abilities;
        this.weight_kg = weight_kg;
        this.height_m = height_m;
        this.captureRate = capture;
        this.isLegendary = isLegendary;
        this.capture_date = data;
    }

    public Pokemon(){

        this.id = 0;
        this.generation = 0;
        this.name = "";
        this.description = "";
        this.types =  new ArrayList<>();
        this.abilities =  new ArrayList<>();
        this.weight_kg = 0;
        this.height_m = 0;
        this.captureRate = 0;
        this.isLegendary = false;
        this.capture_date = "";

    }

    /*
     * gets
     */

     public  int getId(){return id;}
     public int getGeneration(){return generation;}
     public String getName(){return name;}
     public String getDescription(){return description;}
     public double getweight_kg(){return weight_kg;}
     public double getheight_m(){return height_m;}
     public int getCaptureRate(){return captureRate;}
     public boolean getIs_legendary(){return isLegendary;}
     public String getcapture_date(){return capture_date;}

     public String getTypes() {
        StringBuilder types = new StringBuilder("['");
    
        for (int i = 0; i < this.types.size(); i++) {
            types.append(this.types.get(i));
    
            if (i < this.types.size() - 1) {
                types.append("', '");  // Adicionar vírgula e espaço entre os tipos
            }
        }
    
        types.append("']");  // Fechar com aspas simples
    
        return types.toString();
    }
    
    public String getAbilities() {
        StringBuilder abilities = new StringBuilder("[");
    
        for (int i = 0; i < this.abilities.size(); i++) {
            abilities.append(this.abilities.get(i));
    
            if (i < this.abilities.size() - 1) {
                abilities.append(", ");  // Adicionar vírgula e espaço entre as habilidades
            }
        }
    
        abilities.append("]");  // Fechar com aspas simples
    
        return abilities.toString();
    }

    /*
     * sets
     */

    public void setId(int id){this.id = id;}
    public void setGeneration(int generation){this.generation = generation;}
    public void setName(String nome){this.name = nome;}
    public void setDescription(String descri){this.description = descri;}
    public void setweight_kg(double weight_kg){this.weight_kg = weight_kg;}
    public void setheight_m(double height_m){this.height_m = height_m;}
    public void setCaptureRate(int capture){this.captureRate = capture;}
    public void setIs_Legendary(boolean resp){this.isLegendary = resp;}
    public void setcapture_date(String data){this.capture_date = data;}
    public void setTypes(ArrayList<String> lista){this.types = lista;}
    public void setAbilities(List<String> lista){this.abilities = lista;}

 } 

public static void main(String[] args)throws Exception{

    Main main = new Main();

    Lista listaTotal = main.new Lista(801);
    Pilha stack = main.new Pilha();

    listaTotal.LeituraArquivo("/tmp/pokemon.csv");

    //Lista entrada = main.new Lista(50);

    Scanner sc = new Scanner(System.in);

    while(true){
    String id = sc.nextLine();

    if (id.equals("FIM")) {
        break; 
    }

    int id_num = Integer.parseInt(id);

    // Percorra a lista de pokemons para encontrar o ID correspondente
    boolean encontrado = false;
    for (int i = 0; i < 801; i++) {
        Pokemon pokemon = listaTotal.array[i];
        if (pokemon.getId() == id_num) {
            stack.empilhar(pokemon);
            encontrado = true;
            break; // Se encontrou, sai do loop de busca
        }
    }
    if (!encontrado) {
        System.out.println("Pokemon não encontrado com o ID fornecido.");
     }
    }

    //Operacoes de insercao e remocao

    int n = sc.nextInt();

    sc.nextLine(); // Limpar o buffer

 for (int i = 0; i < n; i++) {
    String comando = sc.next();

    if (comando.equals("I")) {
        int id = sc.nextInt();
        Pokemon tmp = listaTotal.PokemonID(id);
        stack.empilhar(tmp);
        
    } else if (comando.equals("R")) {
        stack.desempilhar();

    }  

}

sc.close();

for (int i = 0; i <count_removidos; i++) {
    if (removidos[i] != null) {
        System.out.println("(R) " + removidos[i].getName());
    }
}

stack.Mostrar();

    
}


}