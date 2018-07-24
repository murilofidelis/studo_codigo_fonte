#!/bin/sh

set -e

# Perform all actions as $POSTGRES_USER
export PGUSER="postgres"

# Cria banco
psql <<- 'EOSQL'
CREATE DATABASE db_studo;
EOSQL

# Cria schemas
psql --dbname="db_studo" <<- 'EOSQL'
CREATE SCHEMA IF NOT EXISTS studo AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS usuarios AUTHORIZATION postgres;
EOSQL
