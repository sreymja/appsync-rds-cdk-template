# appsync-rds-cdk-template

This project is intended as a starter for setting up a serverless API with a frontend consuming it. It's made up of the
following modules. It's primary purpose is as a POC to help evaluate the technology.

- [generate](generate/README.md) - generates code for appsync setup from the provided sql schema
- [infra](infra/README.md) - infrastructure as code for project written in CDK
- [react-frontend](react-frontend/README.md) - frontend app 
- [sql-lambda](sql-lambda/README.md) - lambda for deploying sql schema to RDS
- [sql-scripts](sql-scripts/README.md) - scripts for defining sql schema

## Deploying the app
See the instructions in the [infra](infra/README.md) module

## Using as a template
If you have your own sql schema you wish to apply to the template much of the code can be regenerated to reduce the
re-writing of code needed to facilitate the new schema. See the instructions in the [sql-scripts](sql-scripts/README.md)
module to change the schema and regenerate the backend code. Once the backend code is re-generated and redeployed the
frontend data access code can be regenerated (see [react-frontend](react-frontend/README.md)). The rest of the frontend
would then need changing to reflect the purpose of the new schema

## Blogs
More detail on the purpose and process of the project can be found in the blogs written about the project;
- [Appsync (backend)](Blog-Appsync.md)
- 
