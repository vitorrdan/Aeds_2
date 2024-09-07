#include <stdio.h>

int main() {
    int n;
    double num;

    scanf("%d", &n);

    // Abertura do arquivo para escrita em binário
    FILE *file = fopen("arquivo.txt", "wb");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo para escrita.\n");
        return 1;
    }

    for (int i = 0; i < n; i++) {
        scanf("%lf", &num);
        fwrite(&num, sizeof(double), 1, file);  // Gravação binária
    }

    fclose(file);

    // Reabrir o arquivo para leitura
    file = fopen("arquivo.txt", "rb");
    if (file == NULL) {
        printf("Erro ao abrir o arquivo para leitura.\n");
        return 1;
    }

    fseek(file, 0, SEEK_END);

    // Ler e imprimir os valores de trás para frente
    for (int i = 0; i < n; i++) {
        fseek(file, -(i + 1) * sizeof(double), SEEK_END);
        fread(&num, sizeof(double), 1, file);
        printf("%g\n", num);
    }

    fclose(file);

    return 0;
}
