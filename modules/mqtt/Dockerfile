FROM openjdk:17-jdk-slim

WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY build/libs/*.jar app.jar

# 포트 설정
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
