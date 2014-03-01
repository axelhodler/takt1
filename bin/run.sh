#!/bin/sh

# Set the port for the webapp
# only necessary when running locally
export PORT=5000
export NAME=takt1
export SERVER=irc.quakenet.org
export CHANNEL=#takt1
export IDENT=test
export PASSWORD=test
export RESTAPIURL=test

mvn exec:java -Dexec.mainClass="org.xorrr.bot.Main"
