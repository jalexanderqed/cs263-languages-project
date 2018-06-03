#!/bin/bash

if [ $# -eq 0 ]
then
    echo "Usage: ./invoke.sh <function name> <iterations> <iteration delay> <input int> <intput string>"
    exit 1
fi

mvn compile && mvn exec:java -Dexec.args="$1 $2 $3 $4 $5"
