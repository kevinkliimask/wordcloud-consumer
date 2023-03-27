# WordcloudConsumer

## Run locally

Start a RabbitMQ container by running `docker run -d --hostname my-rabbit --name my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management`.
Start a Postgres container by running `docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres`.
Run `./gradlew bootRun` for a dev server. Server is located at `http://localhost:8081/`.

## Run in Docker

Run `docker compose up` for a Docker container that runs the application. Server is located at `http://localhost:8081/`.

## TODO list

- Set up RabbitMQ to have better consistency. There is a bug where sometimes the Dockerized application can't connect 
with RabbitMQ, usually restarting the container or sending a few requests will fix this. Need to troubleshoot this.
- Fix Docker configurations. Ideally running `docker compose -f .\wordcloud-consumer\docker-compose.yml -f .\wordcloud-producer\docker-compose.yml up -d` 
would start both the producer and consumer applications, but for some reason consumer application will fail to get connection to RabbitMQ.
Could be due to a race condition. Have to troubleshoot this.

Copied from wordcloud-producer repo:
- Add e2e tests. Need to research this, haven't written many tests in Java before, especially for Java web applications. Potential libraries: JUnit, REST Assured, Mockito.
- Refactor codebase to use Java best practices. For example, naming conventions.
- Add proper DTO classes abd validation for incoming payloads. Potential package to use: Hibernate validation.
- Add environment variables.
- Add production support.