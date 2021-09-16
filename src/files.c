#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
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

uint8_t file_exists(char* file) {
    return access(file, F_OK) == 0;
}

uint8_t file_directory(char* file) {
    struct stat f_stat;
    stat(file, &f_stat);
    return S_ISDIR(f_stat.st_mode);
}