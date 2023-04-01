#!/usr/bin/env bash

psql -d "$1" <<EOF
CREATE DATABASE testdb;
CREATE USER program WITH PASSWORD 'test';
GRANT ALL PRIVILEGES ON DATABASE testdb TO program;
GRANT ALL PRIVILEGES ON SCHEMA public TO program;
EOF
