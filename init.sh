#!/bin/zsh
read -r "?access key id: " access_key_id
read -r "?secret access key: " secret_access_key

echo "inputs $access_key_id, $secret_access_key"

export AWS_PROFILE=cloud-guru

aws configure set aws_access_key_id "$access_key_id"
aws configure set aws_secret_access_key "$secret_access_key"
aws configure set region "us-east-1"