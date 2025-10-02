# 第一阶段：编译阶段（使用JDK镜像，因为需要javac编译）
FROM maven:3.9-amazoncorretto-17-al2023 AS builder

# 复制项目文件到编译容器的工作目录
WORKDIR /coub-algorithm
COPY pom.xml .
COPY src ./src

# 编译并打包（生成JAR到 /build/target/ 目录）
RUN mvn clean package -DskipTests

# 第二阶段：运行阶段（使用轻量级JRE镜像，仅复制编译好的JAR）
FROM openjdk:17-ea-33-slim

WORKDIR /app
# 从第一阶段的builder容器中复制JAR包（仅复制最终产物，减小体积）
COPY --from=builder /coub-algorithm/target/coub-algorithm-1.0.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]