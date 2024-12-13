package TP04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Alvinegra {

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

class No {
    private Pokemon elemento;
    private No esq, dir;
    private boolean cor;

    public No(Pokemon x) {
        this.elemento = x;
        this.esq = this.dir = null;
        this.cor = true; // Novo nó sempre inicia como vermelho
    }
}

private No raiz;
private final boolean VERMELHO = true;
private final boolean PRETO = false;

public Alvinegra() {
    raiz = null;
}

public void inserir(Pokemon x) {
    raiz = inserir(raiz, x);
    raiz.cor = PRETO; // A raiz deve ser sempre preta
}

private No inserir(No h, Pokemon x) {
    if (h == null) {
        return new No(x);
    }

    if (x.getName().compareTo(h.elemento.getName()) < 0) {
        h.esq = inserir(h.esq, x);
    } else if (x.getName().compareTo(h.elemento.getName()) > 0) {
        h.dir = inserir(h.dir, x);
    } else {
        return h;
    }

    if (isRed(h.dir) && !isRed(h.esq)) {
        h = rotacaoEsquerda(h);
    }
    if (isRed(h.esq) && isRed(h.esq.esq)) {
        h = rotacaoDireita(h);
    }
    if (isRed(h.esq) && isRed(h.dir)) {
        trocaCores(h);
    }

    return h;
}

private boolean isRed(No h) {
    return h != null && h.cor == VERMELHO;
}

private No rotacaoEsquerda(No h) {
    No x = h.dir;
    h.dir = x.esq;
    x.esq = h;
    x.cor = h.cor;
    h.cor = VERMELHO;
    return x;
}

private No rotacaoDireita(No h) {
    No x = h.esq;
    h.esq = x.dir;
    x.dir = h;
    x.cor = h.cor;
    h.cor = VERMELHO;
    return x;
}

private void trocaCores(No h) {
    h.cor = VERMELHO;
    h.esq.cor = PRETO;
    h.dir.cor = PRETO;
}

public boolean pesquisar(String nome) {
    System.out.println(nome);
    System.out.print("raiz ");
    return pesquisar(raiz, nome);
}

private boolean pesquisar(No h, String nome) {
    if (h == null) {
        return false;
    }

    if (nome.compareTo(h.elemento.getName()) < 0) {
        System.out.print("esq ");

        return pesquisar(h.esq, nome);
    } else if (nome.compareTo(h.elemento.getName()) > 0) {
        System.out.print("dir ");

        return pesquisar(h.dir, nome);
    } else {
        return true;
    }
}

public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    Alvinegra arvore = new Alvinegra();
    Pokemon pokemon = arvore.new Pokemon();
    List<Pokemon> pokemons = pokemon.LeituraArquivo("/tmp/pokemon.csv");

    String entrada;

    while (true) {
        entrada = sc.nextLine();
        if (entrada.equals("FIM")) {
            break;
        }

        int id_entrada = Integer.parseInt(entrada);
        for (Pokemon i : pokemons) {
            if (i.getId() == id_entrada) {
                arvore.inserir(i);
            }
        }
    }

    String nome_entrada;

    while (true) {
        nome_entrada = sc.nextLine();
        if (nome_entrada.equals("FIM")) {
            break;
        }
        System.out.println(arvore.pesquisar(nome_entrada) ? "SIM" : "NAO");
    }

    sc.close();
}
}






