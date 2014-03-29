#!/bin/sh

export PORT=5000
export NAME=test
export SERVER=test
export CHANNEL=test

# Optional:
export IDENT=test
export PASSWORD=test

mvn integration-test
