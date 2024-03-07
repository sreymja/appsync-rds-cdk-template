

# TODO

- s3 sync with migrations folder on deploy
- s3 write triggers activation of migration lambda
- migration lambda checks for existence of migration table.
  - if no create table
  - if yes check folder contents against applied migrations
- apply all un-applied migrations in folder
- add newly applied records to migrations table
- do them one at a time in a single transaction per migration & table update

### infra for above
- s3 bucket (put in it's own stack)
- trigger for s3 bucket *** not yet done ***
- lambda
- rds cluster