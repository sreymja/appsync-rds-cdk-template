# appsync-rds-cdk-template
A starter project for a graphql appsync endpoint with RDS resolvers and a Aurora postgres db
![AWS AppSync with RDS Resolver](images/AppSync-Aurora.jpg)

## TODO
1) scripts to run locally with local stack
2) local tests (localstack??)
3) rest api gateway template
4) post deploy tests
5) versioning for lambdas to ensure deployment


```shell
npx graphql-schema-utilities -s "{infra/src/main/resources/gen/schema.graphql,infra/src/main/resources/*.graphql}" \
  -o "infra/src/main/resources/merged/schema.graphql"
```