linux:
	mkdir -p out
	gcc $(shell find src/ -name "*.c") -o out/mcfunctionext
	cp -r src/assets out/assets

all: linux