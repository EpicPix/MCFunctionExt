mkdir -p out
cd src
gcc $(find . -name "*.c") -o ../out/mcfunctionext
cp -r assets ../out/assets