FROM openjdk:17-ea-33-slim  AS builder

WORKDIR /app
# 从第一阶段的builder容器中复制JAR包（仅复制最终产物，减小体积）
COPY target/remote-mq-consumer-1.0.0-SNAPSHOT.jar app.jar

RUN groupadd -r spring && useradd -r -g spring spring
USER spring

ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC"
EXPOSE 8089
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
