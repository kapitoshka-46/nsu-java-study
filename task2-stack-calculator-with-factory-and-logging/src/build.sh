#!/bin/bash
CP="lib/commons-cli-1.11.0.jar"
SRC="src"
OUT="out"

mkdir -p "$OUT"

javac -cp "$CP" -d "$OUT" "$SRC"/jcalc/*.java
