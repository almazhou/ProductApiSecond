#!/bin/bash

PASSWORD=twer psql -h "localhost" -U "postgres" -c "drop database giant"
PASSWORD=twer psql -h "localhost" -U "postgres" -c "create database giant"
PASSWORD=twer psql -h "localhost" -U postgres < createTable.sql