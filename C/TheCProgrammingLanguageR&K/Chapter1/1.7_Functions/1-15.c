#include <stdio.h>

#define MAXTEMPERATURE 300

/* temperature conversion programm with a function to convert temperature */

float ftoc(int f);

int main() {
    int f;
    for (f = 0; f <= MAXTEMPERATURE; f += 20) {
        printf("%3d %2.1f\n", f, ftoc(f));
    }
}

float ftoc(int f) {
    return ((5.0/9.0)*(f-32));
}