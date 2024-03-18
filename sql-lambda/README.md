# sql-lambda
Lambda for deploying sql scripts to RDS. Scripts are uploaded to s3 bucket which raise an event
which triggers the lambda. The lambda runs the file(s) found in the event details. For examples of usage
see the deploy.sh file in the infra folder and the load-data.sh file in the sql-scripts folder.
