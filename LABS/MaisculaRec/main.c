#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <stdlib.h>

bool isMaiuscula(char c){
    return (c >= 'A' && c <= 'Z');
}

//Metodo recursivo para contar
int count_M(char* palavra) {
    //Parada quando chegar no final da string
    if (*palavra == '\0') {
        return 0;
    }

    int resp = isMaiuscula(*palavra) ? 1 : 0;

    // chamada para proximo caractere
    return resp + count_M(palavra + 1);
}

int main() {
    char entrada[100];

    while (true) {

        scanf(" %[^\n]", entrada);

        if (strcmp(entrada, "FIM") == 0) {
            break;
        }

        int resp = count_M(entrada);
        printf("%d\n", resp);
    }

    return 0;
}
