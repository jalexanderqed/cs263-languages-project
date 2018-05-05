#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply function name and class name"
    exit 1
fi

aws lambda update-function-code \
--region us-west-1 \
--function-name $1 \
--zip-file fileb://pythonPi.zip


