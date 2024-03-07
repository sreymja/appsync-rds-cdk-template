apiKey=$(cat outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"app-sync-stack\"][\"ApiKey\"])")
graphqlUrl=$(cat outputs.json | python3 -c "import json,sys;print(json.load(sys.stdin)[\"app-sync-stack\"][\"GraphqlUrl\"])")

cat > ../react-frontend/src/appsync-config.js <<EOM
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