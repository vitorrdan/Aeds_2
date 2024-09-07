#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool isFIM(const char* palavra) {
    return strlen(palavra) == 3 && palavra[0] == 'F' && palavra[1] == 'I' && palavra[2] == 'M';
}

bool static isPalindromo(char* palavra){
    bool resp = false;
    int len = strlen(palavra);
    char copia[len + 1];

    for(int i = 0; i < len; i++){
        copia[i] = palavra[len - 1 - i];
    }
    copia[len] = '\0';

    if(strcmp(palavra, copia) == 0){
        resp = true;
    }

    return resp;
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

        if (isPalindromo(palavra)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}
