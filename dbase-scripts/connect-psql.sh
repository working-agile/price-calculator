#!/bin/bash

#----------------------------------------------------
#
# Connect via psql on the running postgres container
#
#----------------------------------------------------

set -e

echo "----connecting to the postgres database"

docker exec -it dbase-scripts-a-csd-postgres-1 psql -U postgres

