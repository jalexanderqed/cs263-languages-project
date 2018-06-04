#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply function name and script name (without .py)"
    exit 1
fi

rm built.zip

zip built.zip $2.py && \
aws lambda create-function \
--region us-west-1 \
--function-name $1 \
--zip-file fileb://built.zip \
--role arn:aws:iam::065666149143:role/service-role/project-shared \
--runtime python3.6 \
--handler $2.run \
--timeout 15 \
--memory-size 512
