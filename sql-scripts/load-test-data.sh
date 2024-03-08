#!/bin/zsh

export AWS_PROFILE=cloud-guru

prefix=""
sqlScriptsBucket=$(cat ../infra/outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}sql-lambda-stack\"][\"${prefix}SqlScriptsBucket\"])")


aws s3 sync ../sql-scripts/test-data "s3://$sqlScriptsBucket"
