#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool isMaiuscula(char c){
    return(c >= 'A' && c <= 'Z');
}

int count_M(char* palavra){
    int resp = 0;
    int tam = strlen(palavra);
    for(int i = 0; i < tam; i++ ){
        if(isMaiuscula(palavra[i])){
            resp++;
        }
    }

    return resp;
}


int main(){
    char entrada[100];
    
    while(true){
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") == 0){
            break;
        }

        int resp = count_M(entrada);
        printf("%d\n", &resp);


    }

}