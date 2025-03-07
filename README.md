# Paper Crane API

## Prerequisites

- [Docker & Docker Compose](https://docs.docker.com/compose/install/)

## Development

```bash
# start postgres/spring-boot docker image
# db connection - port: 5432 | user: pc | password: password
# app url: http://localhost:8080
docker-compose up

# or add -d for detached mode
docker-compose up -d

# stop (detached mode)
docker-compose down

# check logs (detached mode)
docker-compose logs -f

# check currently running services
docker ps

# access image shell
docker exec -it postgres bash
# connect to db as user "pc"
psql -U pc
# to copy over sql scripts and run them
# the file can then be accessed from the shell using the above instructions
docker cp <file> postgres:/
```

## Notes

- This codebase uses the [Conventional Commits](https://conventionalcommits.org/) specification for commit messages
    - Read guidelines for how to format your commit messages
    - This allows us to generate changelogs and releases via the release workflows
- Development workflow
    - Syncing feature branch with main
        - Avoid using `rebase` with other branches as it does not preserve history and causes other problems as well
        - Use: `git merge origin main` while in feature branch, then resolve any conflicts one-by-one
            - (do not force merge as it can potentially delete someone else's work)

### Reference Documentation

For further reference, please consider the following sections:

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/#build-image)
- [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web)

### Guides

The following guides illustrate how to use some features concretely:

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
