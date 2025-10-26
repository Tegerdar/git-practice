/*
    Write a program detab that replaces tabs in input with the proper number of blanks
    to space to the next tab stop. Assume a fixed set of tab stops, say every n columns.
    Should n be a variable or a symbolic parameter?
*/

#include <stdio.h>

#define MAXLINE 1000
#define TABWIDTH 8

int getlineCustom(char* s);
void printlineCustom(char* s, int len);

// Replace tabs in input with proper number of blanks to space to next tab stop. 
// Assumes a fixed set of tab stops, every n columns.

int main() {
    char line[MAXLINE];
    int lineLen = 0;

    while ((lineLen = getlineCustom(line)) != 0) {
        printlineCustom(line, lineLen);
    }
}

// getlineCustom: gets line up to 999 characters long and puts in the end newline

int getlineCustom(char* s) {
    int c;
    int len = 0;

    while ((c = getchar()) != EOF && c != '\n') {
        if (len < MAXLINE-2) {
            s[len] = c;
            ++len;
        }
    }

    if (len > 0) {
        s[len] = '\n';
        ++len;
        s[len] = '\0';
    }
    return len;
}

// printCustom: print line by replacing tabs in input with proper number of blanks to space to next tab stop. 

void printlineCustom(char* s, int len) {
    for (int i = 0; i < len; ++i) {
        if (s[i] == '\t') {
            for (int j = 1; j <= TABWIDTH - (i-1) % TABWIDTH; ++j) {
                putchar(' ');
            }
        } else {
            putchar(s[i]);
        }
    }
}