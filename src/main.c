#include <stdio.h>
#include <errno.h>
#include "files.h"

char* get_file_name(char* a_name) {
    char* pname = a_name;
    char* name = pname;
    while(*name) {
        if(*name == '/') {
            pname = name + 1;
        }
        name++;
    }
    return pname;
}

int main(int argc, char** argv) {
    if(argc < 2) {
        printf("Usage: %s <directory>\n", get_file_name(argv[0]));
        return 1;
    }
    if(!file_exists(argv[1])) {
        printf("This file doesn't exist\n");
        return 1;
    }
    if(!file_directory(argv[1])) {
        printf("File is not a directory!\n");
        return 1;
    }
    printf("todo\n");
}