#!/bin/zsh

apiId=$(cat ../infra/app-sync-outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}app-sync-stack\"][\"ApiId\"])")

rm .graphqlconfig.yml

npx @aws-amplify/cli codegen add --apiId $apiId --region us-east-1
