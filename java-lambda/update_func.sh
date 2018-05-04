#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply function name and class name"
    exit 1
fi

aws lambda update-function-code --function-name $1 \
--zip-file fileb://~/git/cs263-languages-project/java-lambda/target/lambda-java-1.0.jar