# takt1 IRC-bot [![Build Status](https://travis-ci.org/axelhodler/takt1.svg)](https://travis-ci.org/axelhodler/takt1) [![Coverage Status](https://img.shields.io/coveralls/axelhodler/takt1.svg)](https://coveralls.io/github/axelhodler/takt1) [![Dependency Status](https://www.versioneye.com/user/projects/565378f9ff016c003300086c/badge.svg)](https://www.versioneye.com/user/projects/565378f9ff016c003300086c)

The bot is based on the [PircBotX](https://code.google.com/p/pircbotx/)-Java IRC Framework. The only thing it currently does is to scan the channel for lines which contain a URL or a spotify uri. The Bot will then retrieve and post the title of the resource in the channel.
I found other bot functions and modules rather annoying and wanted to create one without these features for usage in a private channel.

## Config
The values are set in `/bin/run.sh` and the other scripts if used

Required values:
* `NAME`          The name of the bot
* `SERVER`        The irc-server on which to connect (e.g. irc.freenode.org)
* `CHANNEL`       The channel which to join (e.g. #foobar)

Optional:
* `IDENT`         The nickserv ident
* `PASSWORD`      The password for the nickserv ident

## Usage
To run locally:

    ./run.sh

## Testing

    mvn test

## Deploy
Create a deployable uber-jar via

    mvn package

I run the bot on the smallest EC2 instance of AWS.

## Release
Invoke the maven-release-plugin

    mvn release:prepare-with-pom

## Troubleshooting
### Deal with german umlauts etc in the title-tag of the HTML page
#### Heroku
Use UTF-8 as LANG environment variable:

    heroku config:add LANG=en_US.UTF-8

#### AWS EC2
Add the following to `/etc/environment`

```shell
LANG=en_US.utf-8
LC_ALL=en_US.utf-8
```
## License

pircbotx is GPL so this project is GPL too
