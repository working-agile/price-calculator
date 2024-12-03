#!/bin/bash

#----------------------------------------------------
#
# Start the postgres database independently
#
#----------------------------------------------------

set -e

echo "----starting the postgres database independently from the services"

export WORKING_AGILE_DATABASE_PATH=/opt/working-agile/a-csd/postgres

DOCKER_COMPOSE_LOCATION=./docker-compose-postgres.yml

if [ -z "$1" ]; then
   echo "please specify 'up' or 'down'"
   exit 1
fi

if [[ $1 == u* ]]; then

    echo "---starting postgres"
    docker compose -f ${DOCKER_COMPOSE_LOCATION} up

elif [[ $1 == d* ]]; then

    echo "---stopping postgres"
    docker compose -f ${DOCKER_COMPOSE_LOCATION} down
fi
