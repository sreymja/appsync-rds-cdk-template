# sql-scripts
Sql scripts are placed in one of the 3 folders. 

The schema folder is for schema changes. Schema files should be prefixed
with the number by which you expect them to be executed. Currently the sql-lambda is not aware of what scripts have
already been applied therefore they must be written to check whether the change they enact has already been applied.

The setup folder is for sql scripts that set up domain data

The test data folder is for sql scripts that contain test data

### Generating/re-generating code
Running the
```shell
./generate-from-schema.sh
```
copies the content of all sql scripts from the schema folder into a single init.sql file. It then executes 
`docker-compose up` with the `-d --wait` flags which makes the script wait until the container is healthy
before continuing. It then runs the generate project with `mvn exec:java` which generates the resolver cdk code
and graphql schema for the sql schema. After stopping the container with `docker-compose down` it uses
`npx graphql-schema-utilities` to merge the generated schema with any manually created custom schema. This merged
schema is the one that's used to generate the AppSync API

### Loading data to RDS
```shell
./load-data.sh
``` 
loads the contents of the setup folder and optionally the test-data folder to s3 where it is processed by the sql lambda
