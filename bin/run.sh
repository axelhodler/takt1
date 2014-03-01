#!/bin/sh

# Set the port for the webapp
# only necessary when running locally
export PORT=5000
export NAME=takt1
export SERVER=irc.quakenet.org
export CHANNEL=#takt1

# Optional:
export IDENT=test
export PASSWORD=test

mvn exec:java -Dexec.mainClass="org.xorrr.bot.Main"
