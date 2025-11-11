#include <stdio.h>

#define IN 1
#define OUT 0
#define WORDCOUNT 100

/* print a histogram of the lengths of words in input
   1) horizontal bars
   2) vertical bars */

int main() {
    int c, state, len, count, maxLen;
    int lengths[WORDCOUNT];
    
    // Initialize array
    for (int i = 0; i < WORDCOUNT; i++) {
        lengths[i] = 0;
    }
    
    len = count = maxLen = 0;
    state = OUT;

    while ((c = getchar()) != EOF && count < WORDCOUNT) {
        if (c == ' ' || c == '\n' || c == '\t') {
            if (state == IN) {
                // End of word - store length
                lengths[count] = len;
                if (len > maxLen) {
                    maxLen = len;
                }
                count++;
                len = 0;
            }
            state = OUT;
        } else {
            if (state == OUT) {
                state = IN;
                len = 1;
            } else {
                len++;
            }
        }
    }

    // Handle last word if file doesn't end with whitespace
    if (state == IN && count < WORDCOUNT) {
        lengths[count] = len;
        if (len > maxLen) {
            maxLen = len;
        }
        count++;
    }

    printf("Vertical Histogram (word counts):\n");
    for (int row = maxLen; row > 0; --row) {
        for (int w = 0; w < count; ++w) {
            printf("%2s", (lengths[w] >= row) ? "#" : " ");
        }
        printf("\n");
    }
}