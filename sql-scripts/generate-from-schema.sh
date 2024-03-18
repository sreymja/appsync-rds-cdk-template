#!/bin/zsh

cat schema/*.sql > init.sql

docker-compose up -d --wait

(
  cd ../generate
  mvn clean package
)

(
  cd ..
  java -jar generate/target/generate-1.0-SNAPSHOT.jar
)

docker-compose down

(
  cd ..
  npx graphql-schema-utilities -s "{infra/src/main/resources/gen/schema.graphql,infra/src/main/resources/*.graphql}" \
    -o "infra/src/main/resources/merged/schema.graphql"
)

