#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>

int main(int argc, char** argv) {
    if(argc < 2) {
        char* pname = argv[0];
        char* name = pname;
        while(*name) {
            if(*name == '/') {
                pname = name + 1;
            }
            name++;
        }
        printf("Usage: %s <directory>\n", pname);
        return 1;
    }
    if(access(argv[1], F_OK) != 0) {
        printf("This file doesn't exist\n");
        return 1;
    }
    struct stat f_stat;
    stat(argv[1], &f_stat);
    if(!S_ISDIR(f_stat.st_mode)) {
        printf("File is not a directory!\n");
        return 1;
    }
    printf("todo\n");
}