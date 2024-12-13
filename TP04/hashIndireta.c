#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#define MAX_POKEMONS 802
#define MAX 50

typedef struct {
  int id;
  int generation;
  char name[100];
  char description[255];
  char types[100];
  char abilities[255];
  double weight;
  double height;
  int captureRate;
  bool isLegendary;
  char captureDate[15];
} Pokemon;

// Variaveis globais
Pokemon pokemons[1000];
Pokemon userPokemons[MAX_POKEMONS];
int userPokemonCount = 0;
Pokemon removidos[50];
int count_removidos = 0;

Pokemon criarPokemon(int id, int generation, char *name, char *description,
                     char *types, char *abilities, double weight, double height,
                     int captureRate, bool isLegendary, char *captureDate) {
  Pokemon p;
  p.id = id;
  p.generation = generation;
  strcpy(p.name, name);
  strcpy(p.description, description);
  strcpy(p.types, types);
  strcpy(p.abilities, abilities);
  p.weight = weight;
  p.height = height;
  p.captureRate = captureRate;
  p.isLegendary = isLegendary;
  strcpy(p.captureDate, captureDate);
  return p;
}

void freeSplit(char **array) {
  int i;
  for (i = 0; strcmp(array[i], "cFIM"); i++) {
    free(array[i]);
  }

  free(array[i]);

  free(array);
}

char **split(char *regex, char *string) {
  int len = strlen(string);
  int n = 1;

  for (int i = 0; i < len; i++) {
    if (string[i] == regex[0])
      n++;
  }

  char **array = (char **)malloc((n + 1) * sizeof(char *));

  for (int i = 0; i < n + 1; i++) {
    array[i] = calloc(200, sizeof(char *));
  }

  strcpy(array[n], "cFIM");

  for (int i = 0; i < n; i++) {
    char *temp = strsep(&string, regex);
    strcpy(array[i], temp);
  }

  return array;
}

char *tratarAbilities(char *array) {
  int tam = strlen(array);
  char *newLine = malloc((tam - 3) * sizeof(char));
  int pos = 0;
  for (int i = 0; i < tam; i++) {
    if (array[i] != '"' && array[i] != '[' && array[i] != ']') {
      newLine[pos] = array[i];
      pos++;
    }
  }

  return newLine;
}

Pokemon ler(char *string) {

  char **array = split(";", string);

  static int pos = 0;
  Pokemon x;

  pokemons[pos].id = atoi(array[0]);
  pokemons[pos].generation = atoi(array[1]);
  strcpy(pokemons[pos].name, array[2]);
  strcpy(pokemons[pos].description, array[3]);
  strcpy(pokemons[pos].types, "'");
  strcat(pokemons[pos].types, array[4]);
  strcat(pokemons[pos].types, "'");
  if (array[5][0] != 0) {
    strcat(pokemons[pos].types, ", '");
    strcat(pokemons[pos].types, array[5]);
    strcat(pokemons[pos].types, "'");
  }

  strcpy(pokemons[pos].abilities, array[6]);
  pokemons[pos].weight = atof(array[7]);
  pokemons[pos].height = atof(array[8]);
  pokemons[pos].captureRate = atoi(array[9]);
  pokemons[pos].isLegendary = array[10][0] == '1' ? true : false;
  strcpy(pokemons[pos].captureDate, array[11]);

  pos++;

  freeSplit(array);

  return x;
}

char *handleLine(char *line) {
  int leng = strlen(line);
  char *formatted = malloc(leng * sizeof(char));
  bool control = true;
  int j = 0;
  for (int i = 0; i < leng; i++) {
    if (line[i] == '"')
      control = !control;
    else if (line[i] == ',' && control)
      formatted[j++] = ';';
    else if (line[i] != '[' && line[i] != ']')
      formatted[j++] = line[i];
  }
  formatted[j] = 0;
  return formatted;
}

void importDB(char *fileName) {
  FILE *arq = fopen(fileName, "r");

  if (arq == NULL) {
    printf("ERRO NO ARQUIVO\n");
    exit(1);
  }

  char line[2000];

  fgets(line, 2000, arq);

  for (int i = 0; fgets(line, 2000, arq); i++) {
    int pos = (int)strcspn(line, "\n\r");
    line[pos] = '\0';

    char *formatted = handleLine(line);

    ler(formatted);
  }

  fclose(arq);
}

void imprimirPokemon(Pokemon pokemons[], int pos) {
  printf("[#%d -> %s: %s - [%s] - [%s] - %.1fkg - %.1fm - %d%% - %s - %d gen] "
         "- %s\n",
         pokemons[pos].id,                             // ID
         pokemons[pos].name,                           // Nome
         pokemons[pos].description,                    // Descrição
         pokemons[pos].types,                          // Tipos
         pokemons[pos].abilities,                      // Habilidades
         pokemons[pos].weight,                         // Peso
         pokemons[pos].height,                         // Altura
         pokemons[pos].captureRate,                    // Taxa de Captura
         pokemons[pos].isLegendary ? "true" : "false", // Legendary (0 ou 1)
         pokemons[pos].generation,                     // Geração
         pokemons[pos].captureDate);                   // Data de Captura
}

// Função para adicionar um Pokémon à lista do usuário
void adicionarPokemon(int id) {
  for (int i = 0; i < MAX_POKEMONS; i++) {
    if (pokemons[i].id == id) {
      userPokemons[userPokemonCount++] = pokemons[i];
      return;
    }
  }
  printf("Pokemon com ID %d nao encontrado.\n", id);
}

// Metodo que retorna um pokemon dado id
Pokemon pokemonID(int id) {
  for (int i = 0; i < MAX_POKEMONS; i++) {
    if (pokemons[i].id == id) {
      return pokemons[i];
    }
  }
}

//------------------------------------------------------------------------------------------
typedef struct Celula {
    Pokemon elemento;
    struct Celula* prox;
} Celula;

Celula* novaCelula(Pokemon p){
  Celula *nova = (Celula*)malloc(sizeof(Celula));
  nova->elemento = p;
  nova->prox = NULL;

  return nova;
}

typedef struct Lista{
    Celula* primeiro;
    Celula* ultimo;

}Lista;


void inicializarLista(Lista* lista) {
    lista->primeiro = NULL;
    lista->ultimo = NULL;
}

void inserirFim(Lista* lista, Pokemon p) {
    Celula* nova = novaCelula(p);
    if (lista->primeiro == NULL) { 
        lista->primeiro = nova;
        lista->ultimo = nova;
    } else {
        lista->ultimo->prox = nova;
        lista->ultimo = nova;
    }
}

bool pesquisar(char* nome, Lista* lista){
  bool resp = false;
  for(Celula* i = lista->primeiro; i != NULL; i = i->prox){
    if(strcmp(i->elemento.name, nome) == 0){
      resp = true;
    }
  }

  return resp;
}

//------------------------------------------------------------------------------------------

// Estrutura da tabela hash
typedef struct {
    Lista* tabela;
    int tamanho;
} Hash;

// Inicializar a tabela hash
void inicializarHash(Hash* hash, int tamanho) {
    hash->tamanho = tamanho;
    hash->tabela = (Lista*)malloc(tamanho * sizeof(Lista));
    for (int i = 0; i < tamanho; i++) {
        inicializarLista(&hash->tabela[i]);
    }
}

// Função de hash
int h(Hash* hash, char* nome) {
    int somaAscii = 0;
    for (int i = 0; nome[i] != '\0'; i++) {
        somaAscii += nome[i];
    }
    return somaAscii % hash->tamanho;
}

// Inserir elemento na tabela hash
void inserirHash(Hash* hash, Pokemon p, char* nome) {
    int pos = h(hash, nome);
    inserirFim(&hash->tabela[pos], p);
}

// Pesquisar elemento na tabela hash
bool pesquisarHash(Hash* hash,char* nome) {
  bool resp;
    int pos = h(hash, nome);
    if(pesquisar(nome,&hash->tabela[pos])){ //chama pesquisar na lista
      printf("=> %s: (Posicao: %d) SIM\n", nome,pos);
      resp = true;
    } else {
      printf("=> %s: NAO\n", nome);
      resp = false;
    }
    return resp;
}


//------------------------------------------------------------------------------------------


int main() {

  importDB("/tmp/pokemon.csv");

  char input[10];
  int id;
  int n;

  Hash hash;
  inicializarHash(&hash, 21);
  

  // Passo 1: Construir a lista de Pokémons com IDs dados pelo usuário até a
  // palavra "FIM"
  while (1) {

    scanf("%s", input);

    if (strcmp(input, "FIM") == 0) {
      break;
    }

    id = atoi(input);
    for (int i = 0; i < MAX_POKEMONS; i++) {
      if (pokemons[i].id == id) {
        inserirHash(&hash,pokemons[i],pokemons[i].name);//inserir na hash
      }
    }
  }

  char nome_entrada[15];

  while(1){
     scanf("%s", nome_entrada);

    if (strcmp(nome_entrada, "FIM") == 0) {
      break;
    }
    //pesquisar na hash
    pesquisarHash(&hash,nome_entrada);

  }

 

  return 0;
}