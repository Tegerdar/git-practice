#include <stdio.h>

/* copy input to output, replacing each string of one or more blanks by a single blank */

int main(void) {
    int c, prev = 0;

    while ((c = getchar()) != EOF) {
        if (c == ' ' && prev == ' ')
            continue;  // skip extra spaces
        putchar(c);
        prev = c;
    }

    return 0;
}