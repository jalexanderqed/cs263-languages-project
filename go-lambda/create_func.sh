#!/bin/bash

if [ $# -eq 0 ]
  then
    echo "Please supply function name and executable name"
    exit 1
fi

aws lambda create-function --region us-west-1 --function-name $1 \
--zip-file fileb://upzip.zip \
--role arn:aws:iam::065666149143:role/service-role/project-shared \
--handler $2 --runtime go1.x
