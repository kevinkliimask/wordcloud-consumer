# WordcloudConsumer

## Run locally

Start a RabbitMQ container by running `docker run -d --hostname my-rabbit --name my-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management`.
Start a Postgres container by running `docker run --name postgres -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -d postgres`.
Run `./gradlew bootRun` for a dev server. Server is located at `http://localhost:8081/`.

## Run in Docker

Run `docker compose up` for a Docker container that runs the application. Server is located at `http://localhost:8081/`.

# How the application works

Producer app has 2 endpoints - GET mapping and POST mapping. When you upload a file, the file gets sent to producer application,
which will forward the contents of the file via a message to consumer application. Consumer application will count the words
and will persist the data. After uploading the file, you are redirected to a page with a unique identifer for the database entry.
The page will then send a GET request with the ID to producer app, which will forward it to consumer app and it will in
turn return the counts of the words.

## TODO list

- Set up RabbitMQ to have better consistency. There is a bug where sometimes the consumer application can't retrieve messages, 
usually restarting the application and purging the queue from RabbitMQ management or sending a new message will fix this. 
Need to troubleshoot this.
- Fix Docker configurations. Ideally running `docker compose -f .\wordcloud-consumer\docker-compose.yml -f .\wordcloud-producer\docker-compose.yml up -d` 
would start both the producer and consumer applications, but for some reason consumer application will fail to get connection to RabbitMQ.
Could be due to a race condition. Have to troubleshoot this.

Copied from wordcloud-producer repo:
- Add e2e tests. Need to research this, haven't written many tests in Java before, especially for Java web applications. Potential libraries: JUnit, REST Assured, Mockito.
- Refactor codebase to use Java best practices. For example, naming conventions.
- Add proper DTO classes abd validation for incoming payloads. Potential package to use: Hibernate validation.
- Add environment variables.
- Add production support.