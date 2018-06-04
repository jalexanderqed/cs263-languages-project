#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply program name (e.g. main, not main.go)"
    exit 1
fi

rm upzip.zip

GOOS=linux go build $1.go && zip upzip.zip $1
