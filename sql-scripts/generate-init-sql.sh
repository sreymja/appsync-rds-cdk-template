#!/bin/zsh

cat schema/*.sql > init.sql
cat test-data/*.sql >> init.sql