#!/bin/zsh
read -r "?stack prefix [default='']: " prefix
prefix=${prefix:-''}

sqlScriptsBucket=$(cat ../infra/outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}sql-lambda-stack\"][\"${prefix}SqlScriptsBucket\"])")

read -r "?do you want to load test data (y/N)? " testData
if [[ "$testData" =~ ^[Yy]$ ]]
then
  aws s3 sync ../sql-scripts/test-data "s3://$sqlScriptsBucket"
fi

aws s3 sync ../sql-scripts/setup "s3://$sqlScriptsBucket"
