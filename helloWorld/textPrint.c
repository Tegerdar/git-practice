#include <stdio.h>

int main() {
    FILE *text = fopen("text.txt", "r");
    if (text == NULL) {
        printf("Error: couldn't open file.");
        return 1;
    }
    
    stdin = text;
    int c;

    while ((c = getchar()) != EOF) {
        putchar(c);
    }
}