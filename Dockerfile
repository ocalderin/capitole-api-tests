FROM amazoncorretto:17.0.9-alpine3.18

WORKDIR /app

#VOLUME ./reports
#VOLUME ./data
VOLUME
VOLUME /app/data

COPY pom.xml mvnw entrypoint.sh ./
COPY .mvn .mvn

RUN ./mvnw dependency:go-offline

COPY src ./src

ENTRYPOINT ["/bin/sh"]
CMD ["entrypoint.sh"]