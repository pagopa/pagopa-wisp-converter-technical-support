#
# Build
#
FROM maven:3.9.3-amazoncorretto-17@sha256:4ab7db7bd5f95e58b0ba1346ff29d6abdd9b73e5fd89c5140edead8b037386ff AS buildtime
WORKDIR /build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

#
# Package stage
#
FROM --platform=linux/amd64 amazoncorretto:17.0.9-alpine3.18@sha256:df48bf2e183230040890460ddb4359a10aa6c7aad24bd88899482c52053c7e17 as builder
COPY --from=buildtime /build/target/*.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM ghcr.io/pagopa/docker-base-springboot-openjdk17:v2.2.2sha256:0f86bdeba06c487afad2bb45507cbc7529c3d5abc6652a5ae5144fe6004c7159

COPY --chown=spring:spring  --from=builder dependencies/ ./
COPY --chown=spring:spring  --from=builder snapshot-dependencies/ ./

# https://github.com/moby/moby/issues/37965#issuecomment-426853382
RUN true
COPY --chown=spring:spring  --from=builder spring-boot-loader/ ./
COPY --chown=spring:spring  --from=builder application/ ./