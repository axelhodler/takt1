# takt1 IRC-bot
The bot is based on the [PircBotX](https://code.google.com/p/pircbotx/)-Java IRC Framework. The only thing it currently does is scanning the channel for lines which contain a URL. The Bot will then retrieve and post the title of the Url in the channel. I found other bot functions and modules rather annoying and wanted to create one without these features for usage in a private channel.

## Config
Edit the `config.properties` with your settings. The AUTH (ident/password) ist currently not implemented.

## Running locally
Run the script `run.sh`.

## Heroku
The Bot contains a webapp so it can be deployed on [heroku](https://heroku.com). To prevent the webapp from sleeping the bot will access it once every 30 minutes. 

For the Deployment see [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java). A `Procfile` and `system.properties` to tell the Plattform to use Java 1.7. already exists.

### Deal with german umlauts etc in the title-tag of the HTML page
Use UTF-8 as LANG environment variable:

    heroku config:add LANG=en_US.UTF-8