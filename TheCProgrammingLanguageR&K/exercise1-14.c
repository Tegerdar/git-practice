#include <stdio.h>

#define ASCIILEN 128

/* print a histogram of the frequincies of different characters in input */

int main() {
    int c;
    int characters[ASCIILEN];

    for (int i = 0; i < ASCIILEN; i++) {
        characters[i] = 0;
    }

    while ((c = getchar()) != EOF) {
        ++characters[c];
    }
    
    for (int i = 32; i < ASCIILEN; ++i) {
        if (characters[i] > 0) {  // Only print characters that appear
            // Show character representation
            if (i == ' ') {
                printf("SPC | ");
            } else if (i == '\t') {
                printf("TAB | ");
            } else {
                printf(" %c  | ", i);
            }
            
            // Print bars
            for (int j = 0; j < characters[i]; ++j) {
                printf("#");
            }
            printf("\n");
        }
    }
}