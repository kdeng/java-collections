#!/bin/bash -e

cd /app
java -version
mvn --version
mvn -B -U -T2C clean verify
