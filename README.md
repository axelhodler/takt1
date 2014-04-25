# takt1 IRC-bot
[![Build Status](https://travis-ci.org/xorrr/takt1.png)](https://travis-ci.org/xorrr/takt1)

The bot is based on the [PircBotX](https://code.google.com/p/pircbotx/)-Java IRC Framework. The only thing it currently does is scanning the channel for lines which contain a URL. The Bot will then retrieve and post the title of the Url in the channel. I found other bot functions and modules rather annoying and wanted to create one without these features for usage in a private channel.

## Config
The values are set in `/bin/run.sh` and the other scripts if used

Required values:
* `PORT`          The port in which to run the bot
* `NAME`          The name of the bot
* `SERVER`        The irc-server on which to connect (e.g. irc.freenode.org)
* `CHANNEL`       The channel which to join (e.g. #foobar)

Optional:
* `IDENT`         The nickserv ident
* `PASSWORD`      The password for the nickserv ident

## Execute
To run locally:

    ./bin/run.sh

## Testing
Run the tests via:

    ./bin/run_tests.sh

## Deploy
The Bot contains a simple webapp so it can be deployed on [heroku](https://heroku.com).

To set the necessary env vars for heroku invoke the script:

    ./bin/set_heroku_env_vars.sh

To prevent the webapp from sleeping you can for example use [uptimerobot](http://uptimerobot.com/) or any of the other multiple solutions.

## Release
Source the environment variables for testing

    . ./bin/set_testing_env_vars.sh

Invoke the maven-release-plugin

    maven release:prepare-with-pom

## Troubleshooting
### Deal with german umlauts etc in the title-tag of the HTML page
Use UTF-8 as LANG environment variable:

    heroku config:add LANG=en_US.UTF-8

## License

pircbotx is GPL so this project is GPL too