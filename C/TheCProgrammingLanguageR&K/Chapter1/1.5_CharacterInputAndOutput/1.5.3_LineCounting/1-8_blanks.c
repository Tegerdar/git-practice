#include <stdio.h>

/* count blanks in input */

int main() {
    int c, bl;
    bl = 0;
    while ((c = getchar()) != EOF)
    {
        if (c == ' ') {
            ++bl;
        }
    }
    printf("%d\n", bl);
}