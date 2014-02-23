#!/bin/sh

# Set the port for the webapp
# only necessary when running locally
export PORT=5000

mvn exec:java -Dexec.mainClass="Main"
