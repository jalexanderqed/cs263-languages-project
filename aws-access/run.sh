#!/bin/bash

mvn compile && mvn exec:java -Dexec.args="$1 1 0 100000 test"
