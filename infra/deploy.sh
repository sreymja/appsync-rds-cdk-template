#!/bin/zsh

read -r "?stack prefix [default='']: " prefix
prefix=${prefix:-''}

read -r "?do you need to bootstrap (y/N)? " bootstrap
if [[ "$bootstrap" =~ ^[Yy]$ ]]
then
  cdk bootstrap
fi

cdk deploy "${prefix}vpc-stack" "${prefix}database-stack" "${prefix}sql-lambda-stack" --parameters prefix="$prefix" \
  --outputs-file outputs.json

sqlScriptsBucket=$(cat outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}sql-lambda-stack\"][\"${prefix}SqlScriptsBucket\"])")

aws s3 sync ../sql-scripts/schema "s3://$sqlScriptsBucket"

cdk deploy "${prefix}app-sync-stack" --parameters prefix="$prefix" --outputs-file app-sync-outputs.json

apiKey=$(cat app-sync-outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}app-sync-stack\"][\"ApiKey\"])")
graphqlUrl=$(cat app-sync-outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}app-sync-stack\"][\"GraphqlUrl\"])")

cat > ../react-frontend/src/appsync-config.js  <<EOM
const config =  {
    API: {
        GraphQL: {
          endpoint: '${graphqlUrl}',
          region: 'us-east-1',
          defaultAuthMode: 'apiKey',
          apiKey: '${apiKey}'
        }
    }
};

export default config;
EOM

(
  cd ../react-frontend || exit
  npm run build
)

cdk deploy "${prefix}frontend-stack" --parameters prefix="$prefix" --outputs-file frontend-outputs.json

frontendBucket=$(cat frontend-outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"${prefix}frontend-stack\"][\"BucketName\"])")

aws s3 sync ../react-frontend/build "s3://${frontendBucket}"