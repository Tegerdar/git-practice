/*
    Write a function reverse(s) that reverses the character string s.
    Use it to write a program that reverses its input a line at a time.
*/

#include <stdio.h>

int getlineCustom(char* s);
void printlineReverse(char* s, int len);

#define MAXLINE 1000

// read each line and print it in reverse

int main() {
    char line[MAXLINE];
    int len;

    while ((len = getlineCustom(line)) != 0) {
        printlineReverse(line, len);
    }
}

// getlineCustom: get input line and return it length

int getlineCustom(char* s) {
    int c;
    int i = 0;

    while ((c = getchar()) != EOF && c != '\n' && i < MAXLINE-1) {
        s[i] = c;
        ++i;
    }

    s[i] = '\0';

    return i;
}

// printlineCustom: print string in reverse

void printlineReverse(char* s, int len) {
    for (int i = len-1; i >= 0; --i) {
        putchar(s[i]);
    }

    putchar('\n');
}