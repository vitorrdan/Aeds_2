#include <stdio.h>

int main(){
    int n;
    double num;

    scanf("%d", &n);

    //Abertura do arquivo para escrita

    FILE *file;
    file = fopen("arquivo.txt", "w");
     if(file == NULL){
      printf("erro ao abrir arquivo");
    }

    //Impresso dos valores lidos no arq

    for(int i = 0; i < n; i++){
        scanf("%lf", &num);
        fprintf(file, "%.3lf\n", num);
    }

    fclose(file);

    //reabertura do arq para ler

    file = fopen("arquivo.txt", "r");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return 1;
    }

    // pular para final 
    fseek(file, 0, SEEK_END);

    // impressao dos valores de tras pra frente
    for (int i = 0; i < n; i++) {
        long pos;

        fseek(file, -2, SEEK_CUR); 
        do {
            pos = ftell(file);
            fseek(file, -1, SEEK_CUR);
        } while (fgetc(file) != '\n' && pos > 1);

        pos = ftell(file);

        fscanf(file, "%lf", &num);
        printf("%.6lf\n", num);

        fseek(file, pos, SEEK_SET);
    }

    fclose(file);


    return 0;
}