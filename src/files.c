#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include "files.h"

uint8_t file_exists(char* file) {
    return access(file, F_OK) == 0;
}

uint8_t file_directory(char* file) {
    struct stat f_stat;
    stat(file, &f_stat);
    return S_ISDIR(f_stat.st_mode);
}