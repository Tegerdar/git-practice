#include <stdio.h>

#define IN 1
#define OUT 0

/* print a histogram of the lenghts of words in input
   1) horizontal bars
   2) vertical bars */

int main() {
    int c, state;
    state = IN;

    while ((c = getchar()) != EOF) {
        if (c == ' ' || c == '\n' || c == '\t') {
            state = OUT;
        } else if (state == OUT) {
            state = IN;
        }
        if (state == IN) {
            putchar('#');
        } else {
            putchar('\n');
        }
    }
}