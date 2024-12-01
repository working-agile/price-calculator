#!/bin/bash

#----------------------------------------------------
#
# Connect via psql on the running postgres container
#
#----------------------------------------------------

set -e

echo "----connecting to the postgres database"

docker exec -it current-server-ssr-working-agile-postgres-1 psql -U postgres

