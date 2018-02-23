# takt1 IRC-bot [![Build Status](https://travis-ci.org/axelhodler/takt1.svg)](https://travis-ci.org/axelhodler/takt1) [![Coverage Status](https://img.shields.io/coveralls/axelhodler/takt1.svg)](https://coveralls.io/github/axelhodler/takt1) [![Dependency Status](https://www.versioneye.com/user/projects/565378f9ff016c003300086c/badge.svg)](https://www.versioneye.com/user/projects/565378f9ff016c003300086c)

The bot is based on the [PircBotX](https://code.google.com/p/pircbotx/)-Java IRC Framework. The only thing it currently does is to scan the channel for lines which contain a URL or a spotify uri. The Bot will then retrieve and post the title of the resource in the channel.
I found other bot functions and modules rather annoying and wanted to create one without these features for usage in a private channel.

## Testing

    mvn test

## Deploy
I run the bot on the smallest EC2 instance of AWS using ansible.

Add your host to `config/ansible/hosts` and run `./build_then_deploy.sh <HOST_IP>`

## License

pircbotx is GPL so this project is GPL too
