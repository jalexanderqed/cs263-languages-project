#!/bin/bash

if [ $# -eq 0 ]
then
    echo "Please supply function name and class name"
    exit 1
fi

./build.sh $2 && \
    aws lambda update-function-code --function-name $1 \
	--zip-file fileb://upzip.zip
