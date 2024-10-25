#!/usr/bin/env bash

psql -d "$1" <<EOF
  CREATE USER program WITH PASSWORD 'test';
  CREATE DATABASE services WITH OWNER program;
EOF
