#include <stdio.h>

/*  copy input to output, replacing each tab by \t, each backspace by \b, and each backslash by \\.
    this makes tabs and backspaces visible in an unambiguous way */

int main() {
    int c;
    while ((c = getchar()) != EOF) {
        if (c == '\b') {
            printf("\\b");
        } else if (c == '\t') {
            printf("\\t");
        } else if (c == '\\') {
            printf("\\\\");
        } else {
            putchar(c);
        }
    }
}