#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* Combinador(char* s1, char* s2) {
    int len1 = strlen(s1);
    int len2 = strlen(s2);
    int tam = len1 + len2;


    char* resultado = (char*)malloc((tam + 1) * sizeof(char));
    int i, j = 0, k = 0;

    for (i = 0; i < tam; i++) {
        if (j < len1) {
            resultado[i] = s1[j++];
        } else if (k < len2) {
            resultado[i] = s2[k++];
        }
    }


    resultado[tam] = '\0';

    return resultado;
}

int main() {

    char s1[100], s2[100];

    fgets(s1, 100, stdin);
    s1[strcspn(s1, "\n")] = '\0';


    fgets(s2, 100, stdin);
    s2[strcspn(s2, "\n")] = '\0';

    char* resultado = Combinador(s1, s2);


    printf("%s\n", resultado);

    free(resultado);

    return 0;
}
