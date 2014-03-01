#!/bin/bash

echo "Set the values of the needed environment variables, don't forget to doublecheck your input"

echo "Set value for the PORT"
read port
heroku config:set PORT=$port

echo "Set value for the NAME of the bot"
read name
heroku config:set NAME=$name

echo "Set value for the irc SERVER (eg. irc.freenode.org)"
read server
heroku config:set SERVER=$server

echo "Set value for the CHANNEL"
read channel
heroku config:set CHANNEL=$channel

echo "Set value for the IDENT (optional)"
read ident
heroku config:set IDENT=$ident

echo "Set value for the PASSWORD to ident (optional)"
read pw
heroku config:set PASSWORD=$pw
