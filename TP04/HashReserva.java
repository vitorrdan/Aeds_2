package TP04;

import java.io.*;
import java.util.*;
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

    /*
     * Metodo clone
     */

    public Pokemon clone(){
        return new Pokemon(this.id, this.generation, this.name, this.description, this.types, this.abilities, this.weight_kg, this.height_m, this.captureRate, this.isLegendary, this.capture_date);

    } 

    /*
     * Metodo imprimir Exemplo: [#181 -> Ampharos: Light Pokémon - ['electric'] - ['Static', 'Plus'] - 61.5kg - 1.4m - 45% - false - 2 gen] - 25/05/1999
     */

     public void imprimir(List<Pokemon> lista,int id){

        for(Pokemon i : lista){
            if(i.id == id){

                System.out.println("[" + "#" + i.id + " -> " + i.name + ": " + i.description + " - " + i.getTypes() + " - " + i.getAbilities() + " - " + i.weight_kg + "kg" + " - " + i.height_m  + "m"+ " - " + i.captureRate + "%" + " - " + i.isLegendary + " - " + i.generation + " gen" + "]" + " - " + i.capture_date);

            }
        }

    }

    public void imprimir(Pokemon[] array,int id){

        for(int i = 0; i < array.length; i++){
            if(id == array[i].id){
                System.out.println("[" + "#" + array[i].id + " -> " + array[i].name + ": " + array[i].description + " - " + array[i].getTypes() + " - " + array[i].getAbilities() + " - " + array[i].weight_kg + "kg" + " - " + array[i].height_m  + "m"+ " - " + array[i].captureRate + "%" + " - " + array[i].isLegendary + " - " + array[i].generation + " gen" + "]" + " - " + array[i].capture_date);

            }
        }

    }

     public void imprimir(Pokemon[] array){

        for(int i = 0; i < array.length; i++){
            
                System.out.println("[" + "#" + array[i].id + " -> " + array[i].name + ": " + array[i].description + " - " + array[i].getTypes() + " - " + array[i].getAbilities() + " - " + array[i].weight_kg + "kg" + " - " + array[i].height_m  + "m"+ " - " + array[i].captureRate + "%" + " - " + array[i].isLegendary + " - " + array[i].generation + " gen" + "]" + " - " + array[i].capture_date);

            
        }

    }





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


}
public class HashReserva {

Pokemon tab[];
int m1,m2,m,reserva;
final Pokemon NULO = null;

public HashReserva(int m1,int m2){
    this.m1 = m1;
    this.m2 = m2;
    this.m = this.m1 + this.m2;
    this.tab = new Pokemon[this.m]; //array com tamaho total 
        for(int i = 0; i < m1; i++){
            tab[i] = NULO;
        }
    reserva = 0;    
}

public int hash(String name){
    int asc = 0;

    for(char c : name.toCharArray()){
        asc += (int) c;
    }

    return asc % m1;
}
//Metodo para inserir na hash, caso nao haja mais pos disponiveis insere na area de reserva
public boolean inserir(Pokemon p){
    boolean resp = false;
    int pos = hash(p.getName());
    if(p != NULO){
        if(tab[pos] == NULO){
        tab[pos] = p;
        resp = true;
    } else if (reserva < m2){
        tab[m1 + reserva] = p;
        reserva++;
        resp = true;
    }
    }
    return resp;
    
}

//Metodo de pesquisa
public boolean pesquisar(String nome){
    boolean resp = false;

    int pos = hash(nome);//endereco do elemento
    if (tab[pos] != NULO && tab[pos].getName().equals(nome)) {//se posicao estiver ocupada com o mesmo nome passado pelo parametro
    resp = true;
   
    System.out.println("=> "+nome+":" + " (Posicao: " + pos + ") SIM");
    } else { //se nao fazer a pesquisa na area de reserva
     for (int i = 0; i < reserva; i++) {
        if (tab[m1 + i] != NULO && tab[m1 + i].getName().equals(nome)) {
            resp = true;
            System.out.println("=> "+nome+":" + " (Posicao: " + (m1 + i) + ") SIM");
            break;
        }
    }
    if (resp == false) {
        System.out.println("=> "+nome+":" + " NAO");
    }
 }
  return resp;
}

public static Pokemon pokemonID(List<Pokemon> lista, int id){
    Pokemon resp = null;
    for(Pokemon i : lista){
        if(i.getId() == id){
            resp = i;
        }
    }

    return resp;
}

public static Pokemon pokemonName(List<Pokemon> lista, String name){
    Pokemon resp = null;
    for(Pokemon i : lista){
        if(i.getName().equals(name)){
            resp = i;
        }
    }
    return resp;
}

public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    HashReserva T1 = new HashReserva(21, 9);
    Pokemon poke = new Pokemon();
    List<Pokemon> lista = poke.LeituraArquivo("/tmp/pokemon.csv");

    String entrada;
    while(true){
        entrada = sc.nextLine();
        if(entrada.equals("FIM")){
            break;
        }
        int id = Integer.parseInt(entrada);
        for(Pokemon p : lista){
            if(p.getId() == id){
                 T1.inserir(p);
            }
        }
       
    }

    String nome_entrada;
    while(true){
        nome_entrada = sc.nextLine();
        if(nome_entrada.equals("FIM")){
            break;
        }
        
        T1.pesquisar(nome_entrada);
    }

    sc.close();
}
    
}
