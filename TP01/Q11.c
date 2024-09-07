#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool isFIM(const char* palavra) {
    return strlen(palavra) == 3 && palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M';
}

bool static isPalindromo(char* palavra, int inicio, int fim){
    if(inicio >= fim){
        return true;
    }

    if(palavra[inicio] != palavra[fim]){
        return false;
    }

    return isPalindromo(palavra, inicio+ 1, fim- 1);
}

int main() {
    char palavra[50];

    while (true) {
        if (fgets(palavra, sizeof(palavra), stdin) == NULL) {
            break;
        }

        palavra[strcspn(palavra, "\n")] = '\0';

        if (isFIM(palavra)) {
            break;
        }

        if (isPalindromo(palavra, 0, strlen(palavra) - 1)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}
