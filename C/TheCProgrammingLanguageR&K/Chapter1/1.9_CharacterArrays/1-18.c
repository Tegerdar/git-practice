/*  Exercise:
    write a program to remove trailing blanks and tabs from each line of input, and to delete entirely blank lines*/

#include <stdio.h>

#define MAXLINE 1000

int getlineCustom(char* s);
void printlineCustom(char* s);

// main: gets input line and convert it into output line without trailing blanks and tabs or deletes entirely blank lines

int main() {
    /*
        1) get line and its length
        2) check if line is entirely blank '\n'
        3) print line bypassing trailing blanks and tabs
    */

    char line[MAXLINE];

    int length;

    while ((length = getlineCustom(line)) != -1) {
        if (length > 0) {
            printlineCustom(line);
        }
    }
}

// getlineCustom: gets the input line and stores it in the given string
//                returns the length of line or -1 if there was EOF

int getlineCustom(char* s) {
    int c; // current character
    int i = 0; // current line length

    while ((c = getchar()) != '\n' && c != EOF) {
        if (i < MAXLINE-2) {
            s[i] = c;
        }
        ++i;
    }

    if (c == EOF)
        return -1;

    if (i > 0) {
        if (i < MAXLINE-2) {
            s[i++] = c;
            s[i++] = '\0';
        } else {
            s[MAXLINE-2] = '\n';
            s[MAXLINE-1] = '\0';
        } 
    } else {
        s[i] = '\0';
    }

    return i;
}

// printlineCustom: prints the given array bypassing the trailing blanks and tabs

void printlineCustom(char* s) {
    int c, prev, i;
    i = 0;
    prev = s[i];

    if (prev != '\t' && prev != ' ')
        putchar(prev);

    while ((c = s[++i]) != '\0') {
        if (c == ' ' || c == '\t') {
            int j = i;

            while (s[j] == ' ' || s[j] == '\t')
                j++;
            if (s[j] == '\n' || s[j] == '\0')
                break;
            else if (prev != ' ' && prev != '\t')
                putchar(' ');
        } else {
            putchar(c);
        }
        prev = c;
    }
}