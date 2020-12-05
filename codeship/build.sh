#!/bin/bash -e

cd /app
java -version
mvn --version
mvn -B -U clean verify
