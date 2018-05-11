#!/bin/bash

mvn compile && mvn exec:java -Dexec.args="pijava 1 0 10000000 test"
