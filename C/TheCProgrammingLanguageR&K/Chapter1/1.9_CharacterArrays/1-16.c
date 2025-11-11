#include <stdio.h>

#define MAXLINE 1000 // maximum input line length

int getline(char line[], int maxline);
void copy(char to[], char from[]);

/* Exercise: 
   Revise the main routine of the lngest-line program so it will correctly print the 
   length of arbitrary long input lines, and as much as possible of the text. */

// print the longest input line

int main() {
    int len; // current line length
    int max; // maximum length seen so far
    char line[MAXLINE]; // current input line
    char longest[MAXLINE]; // longest line saved here

    max = 0;

    while ((len = getline(line, MAXLINE)) > 0) {
        if (len > max) {
            max = len;
            copy(longest, line);
        }
    }

    if (max > 0) { // there was a line
        printf("Length: %d\n", max-1);
        printf("Text: %s", longest);
        return 0;
    }
}

// getline: read a line into s, return length

int getline(char s[], int lim) {
    int c, i;
    for (i = 0;((c=getchar())!=EOF) && c!='\n'; ++i) {
        if (i < lim-1) { 
            s[i] = c;
        }        
    }

    
    if (i >= lim-1) {
        if (c == '\n') {
            s[lim-2] = c;
            ++i;
            s[lim-1] = '\0';
        }
    } else {
        if (c == '\n') {
            s[i] = c;
            ++i;
        }
        s[i] = '\0';
    }
    return i;
}

// copy: copy 'ftom' into 'to'; assume to is big enough

void copy(char to[], char from[]) {
    int i;
    i = 0;
    while ((to[i] = from[i]) != 0) 
        ++i;
}