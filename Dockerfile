FROM openjdk:17
VOLUME /tmp
EXPOSE 8081
ADD build/libs/wordcloud-consumer-1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]