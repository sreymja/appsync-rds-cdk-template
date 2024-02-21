#!/bin/zsh

#cdk synth

export AWS_PROFILE=cloud-guru

read -r "?do you need to bootstrap (y/n)? " bootstrap
if [[ "$bootstrap" =~ ^[Yy]$ ]]
then
  cdk bootstrap
fi

cdk deploy --all
