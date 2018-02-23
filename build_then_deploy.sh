#!/bin/sh

set -eou pipefail

mvn package
cd config/ansible/
ansible-playbook playbook.yaml --extra-vars "host=$1"
