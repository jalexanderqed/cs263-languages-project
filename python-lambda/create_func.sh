#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply function name and executable name"
    exit 1
fi


aws lambda create-function \
--region us-west-1 \
--function-name $1 \
--zip-file fileb://pythonPi.zip \
--role arn:aws:iam::746233676174:role/service-role/role  \
--handler $2 \
--runtime python3.6 \
--timeout 15 \
--memory-size 512

