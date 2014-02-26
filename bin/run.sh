#!/bin/sh

# Set the port for the webapp
# only necessary when running locally
export PORT=5000
export NAME=takt1
export SERVER=irc.freenode.org
export CHANNEL=#takt1
export IDENT=test
export PASSWORD=test
export WEBAPPURL=test
export RESTAPIURL=test

mvn exec:java -Dexec.mainClass="Main"
