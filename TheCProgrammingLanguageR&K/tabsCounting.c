#include <stdio.h>

/* count tabs in input */

int main() {
    int c, tb;
    tb = 0;
    while ((c = getchar()) != EOF)
    {
        if (c == 9) {
            ++tb;
        }
    }
    printf("%d\n", tb);
}