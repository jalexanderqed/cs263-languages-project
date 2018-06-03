#!/bin/bash

if [ $# -eq 0 ]
then
    echo "Please supply function name and class name"
    exit 1
fi

mvn package && \
    aws lambda update-function-code --function-name $1 \
	--zip-file fileb://target/lambda-java-1.0.jar
