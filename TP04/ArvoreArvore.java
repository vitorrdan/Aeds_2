import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArvoreArvore {

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

    /*
     * Metodo leitura do arquivo CSV
     */

    public List<Pokemon> LeituraArquivo(String caminho){
        List<Pokemon> pokemons = new ArrayList<>();

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

            pokemons.add(pokemon);
        }
        sc.close();
    } catch (FileNotFoundException e) {
        System.out.println("Arquivo não encontrado: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Erro ao processar o arquivo: " + e.getMessage());
    }

    return pokemons;
    }

    public static Pokemon pokemonID(List<Pokemon> pokemons,int id){
        Pokemon resp = null;
        for(Pokemon i : pokemons){
            if(i.id == id){
                resp = i;
            }
        }
        return resp;
    }
}

/*
 * Classe No da primeira arvore com ponteiro para No da segunda arvore
 */
    class No {
        private int elemento;
        private No esq, dir;
        private No2 outro;

        public No(int elemento){
            this.elemento = elemento;
            this.dir = this.esq = null;
            this.outro = null;
        }

        public No(int elemento, No esq, No dir, No2 outro){
            this.elemento = elemento;
            this.dir = dir;
            this.esq = esq;
            this.outro = outro;
        }
    }

    /*
     * Classe para No da segunda arvore com 
     */

     class No2 {
        private Pokemon elemento;
        private No2 esq, dir;

        public No2(Pokemon x){
            this.elemento = x;
            this.esq = this.dir = null;
        }

        public No2(Pokemon x, No2 dir, No2 esq){
            this.elemento = x;
            this.esq = esq;
            this.dir = dir;
        }
     }

     /*
      * Classe Arvore de arvore com arovre primaria contendo os mods de captureRate como chave e a segunda arvore pelo nome
       * Arvore 1 contem numeros int dos mods de 15 dos captureRate, e possuem um ponteiro para segunda arvore de pokemons q tera chave de busca o nome dos pokemons
       */

      private No raiz;
      

      public ArvoreArvore(){
        raiz = null;
        
      }
      //metodo para construit arvore inicial 
      public void inserirNoPrimario(int chave) {
        raiz = inserirNoPrimarioRecursivo(chave, raiz);
    }
    
    private No inserirNoPrimarioRecursivo(int chave, No i) {
        if (i == null) {
            i = new No(chave); 
        } else if (chave < i.elemento) {
            i.esq = inserirNoPrimarioRecursivo(chave, i.esq);
        } else if (chave > i.elemento) {
            i.dir = inserirNoPrimarioRecursivo(chave, i.dir);
        }
        return i;
    }

      public void inserir(Pokemon x)throws Exception{
        int chave = (x.captureRate % 15);
        raiz = inserir(chave,raiz, x);
      }

      private No inserir(int chave, No i, Pokemon x)throws Exception{
         if(chave < i.elemento){
            i.esq = inserir(chave,i.esq,x);
        } else if(chave > i.elemento){
            i.dir = inserir(chave,i.dir,x);
        } else { //se ja existir No insere na segunda arvore
            i.outro = inserirArvore2(x,i.outro);
        }
        return i;
      }

      //insercao na segunda arvore por name
      private No2 inserirArvore2(Pokemon x, No2 i)throws Exception{
        if(i == null){
            i = new No2(x);
        } else if(x.name.compareTo(i.elemento.name) < 0){
            i.esq = inserirArvore2(x, i.esq);
        } else if(x.name.compareTo(i.elemento.name) > 0){
            i.dir = inserirArvore2(x, i.dir);
        } else {
            throw new Exception("erro vey");
        }
        return i;
      }


     
      public boolean pesquisar(String nome) {
        System.out.println("=> " + nome);
        System.out.print("raiz ");
        boolean encontrado = pesquisarNaPrimeiraArvore(raiz, nome, "");
        if (encontrado) {
            System.out.println("SIM");
        } else {
            System.out.println("NAO");
        }
        return encontrado;
    }
    
    private boolean pesquisarNaPrimeiraArvore(No i, String nome, String caminho) {
        if (i == null) {
            return false;
        }
    
        System.out.print(caminho);
        boolean encontrado = pesquisarNaPrimeiraArvore(i.esq, nome, caminho + "ESQ ");
        if (encontrado) return true;
    
        encontrado = pesquisarNaSegundaArvore(i.outro, nome, caminho);
        if (encontrado) return true;
    
        System.out.print(caminho);
        encontrado = pesquisarNaPrimeiraArvore(i.dir, nome, caminho + "DIR ");
        if (encontrado) return true;
    
        return false;
    }
    
    private boolean pesquisarNaSegundaArvore(No2 i, String nome, String caminho) {
        if (i == null) {
            return false;
        }
    
        System.out.print("esq ");
        boolean encontrado = pesquisarNaSegundaArvore(i.esq, nome, caminho);
        if (encontrado) return true;
    
        if (i.elemento.name.equals(nome)) {
            return true;
        }
    
        System.out.print("dir ");
        encontrado = pesquisarNaSegundaArvore(i.dir, nome, caminho);
        if(encontrado) return true;
    
        return false;
    }
    

    public static void main(String[] args)throws Exception{
        Scanner sc = new Scanner(System.in);

        ArvoreArvore main = new ArvoreArvore();
        Pokemon pokemon = main.new Pokemon();
        List<Pokemon> pokemons = pokemon.LeituraArquivo("/tmp/pokemon.csv");

        ArvoreArvore tree = new ArvoreArvore();
        //7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12 e 14.
    
    int[] ordemChaves = {7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14};
    for (int chave : ordemChaves) {
        tree.inserirNoPrimario(chave); // Inicializa apenas o nó primário
    }

    String entrada;

    while(true){
        entrada = sc.nextLine();
        if(entrada.equals("FIM")){
            break;
        }

        int id_entrada = Integer.parseInt(entrada);
            for(Pokemon i : pokemons){
                if(i.id == id_entrada){
                    tree.inserir(i);
                }
            }

    }

    String nome_entrada;

    while(true){
        nome_entrada = sc.nextLine();
        if(nome_entrada.equals("FIM")){
            break;
        }
        tree.pesquisar(nome_entrada);
    }

    sc.close();





    }

      



     


    
}
