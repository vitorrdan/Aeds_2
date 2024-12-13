#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
//
typedef struct {
  Pokemon array[MAX];
  int primeiro;
  int ultimo;
  int tamanho;
  int somaCaptureRate;
} FilaCircular;

// Inicializa a fila
void start(FilaCircular *fila) {
  fila->primeiro = 0;
  fila->ultimo = 0;
  fila->tamanho = 0;
  fila->somaCaptureRate = 0;
}

// Verifica se a fila está cheia
bool isFull(FilaCircular *fila) {
  return ((fila->ultimo + 1) % MAX) == fila->primeiro;
}

// Verifica se a fila está vazia
bool isEmpty(FilaCircular *fila) { return fila->primeiro == fila->ultimo; }

// Insere um Pokémon na fila
void inserir(FilaCircular *fila, Pokemon p) {
  if (isFull(fila)) {
    printf("Erro: Fila cheia\n");
    return;
  }
  fila->array[fila->ultimo] = p;
  fila->somaCaptureRate += p.captureRate;
  fila->ultimo = (fila->ultimo + 1) % MAX;
  fila->tamanho++;

  // Calcula e imprime a média
  int media = fila->somaCaptureRate / fila->tamanho;
  printf("Média: %d\n", media);
}

// Remove um Pokémon da fila
Pokemon remover(FilaCircular *fila) {
  if (isEmpty(fila)) {
    printf("Erro: Fila vazia\n");
    exit(1);
  }
  Pokemon p = fila->array[fila->primeiro];
  fila->somaCaptureRate -= p.captureRate;
  fila->primeiro = (fila->primeiro + 1) % MAX;
  fila->tamanho--;

  printf("(R) %s\n", p.name);

  return p;
}

// Metodo para impressao da Lista
void Mostrar(FilaCircular *fila) {
  if (fila->primeiro == fila->ultimo) {
    printf("Fila vazia.\n");
    return;
  }

  int i = fila->primeiro;
  printf("[");

  // Itera pela fila até alcançar o índice 'ultimo'
  while (i != fila->ultimo) {
    printf("[%d] [#%d -> %s: %s - [%s] - [%s] - %.1fkg - %.1fm - %d%% - %s - "
           "%d gen] - %s\n",
           i, fila->array[i].id, fila->array[i].name,
           fila->array[i].description, fila->array[i].types,
           fila->array[i].abilities, fila->array[i].weight,
           fila->array[i].height, fila->array[i].captureRate,
           fila->array[i].isLegendary ? "true" : "false",
           fila->array[i].generation, fila->array[i].captureDate);

    // Avança para o próximo índice de forma modular
    i = (i + 1) % MAX;
  }

  printf("]\n");
}

//------------------------------------------------------------------------------------------

int main() {

  importDB("/tmp/pokemon.csv");
  FilaCircular fila;

  char input[10];
  int id;
  int n;

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
        inserir(&fila, pokemons[i]);
      }
    }
  }

  int pos;

  // Operacoes de insercao e remoçao
  scanf("%d", &n);

  for (int i = 0; i < n; i++) {
    char comando[3];
    int id_2;
    scanf("%s", comando);

    if (strcmp(comando, "I") == 0) {
      scanf("%d", &id_2);
      Pokemon tmp = pokemonID(id_2);
      inserir(&fila, tmp);
    }

    else if (strcmp(comando, "R") == 0) {
      remover(&fila);
    }
  }

  Mostrar(&fila);

  return 0;
}