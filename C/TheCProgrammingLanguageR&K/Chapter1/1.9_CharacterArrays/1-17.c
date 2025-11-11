/*  Exercise:
    Write a program to print all input lines that are longer than 80 characters */

#include <stdio.h>

#define MAXLINE 1000
#define MINLINE 80

int getlineCustom(char s[], int lim);

// write all input lines that are longer than 80 characters

int main() {
    char s[MAXLINE]; // current line
    int len; // current line length
    
    while ((len = getlineCustom(s, MAXLINE))!=0) {
        if (len > MINLINE) {
            printf("\nText: %s", s);
        }
    }
}

// getlineCustom: gets cuurent input line and it length

int getlineCustom(char s[], int lim) {
    int i; // current line length
    int c; // current character
    for (i = 0; (c=getchar())!=EOF && c!='\n'; ++i) {
        if (i < lim-1) {
            s[i] = c;
        }
    }

    if (i >= lim-1) {
        s[lim-2] = '\n';
        s[lim-1] = '\0';
    } else if (c!=EOF) {
        s[i] = '\n';
        ++i;
        s[i] = '\0';
    }

    return i;
}

