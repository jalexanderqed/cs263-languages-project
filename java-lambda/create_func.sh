#!/bin/bash

if [ $# -eq 0 ]
then
    echo "Please supply function name and class name"
    exit 1
fi

mvn package && \
    aws lambda create-function --region us-west-1 --function-name $1 \
	--zip-file fileb://target/lambda-java-1.0.jar \
	--role arn:aws:iam::065666149143:role/service-role/project-shared \
	--handler jalexander.ninja.$2::handler --runtime java8
