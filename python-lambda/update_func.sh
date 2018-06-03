#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply function name and script name (without .py)"
    exit 1
fi

zip built.zip $2.py && \
aws lambda update-function-code \
--region us-west-1 \
--function-name $1 \
--zip-file fileb://built.zip


