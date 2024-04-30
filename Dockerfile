# 빌드 이미지로 OpenJDK 17 & Gradle을 지정
FROM gradle:7.6-jdk17-alpine AS build

# 소스코드를 복사할 작업 디렉토리를 생성
WORKDIR /app

# 라이브러리 설치에 필요한 파일만 복사
COPY build.gradle settings.gradle ./

# 라이브러리만 다운로드
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 호스트 머신의 소스코드를 작업 디렉토리로 복사
COPY . /app

# Gradle 빌드를 실행하여 JAR 파일 생성
RUN gradle clean build -x test --parallel

# 런타임 이미지로 OpenJDK 17-slim 지정
FROM openjdk:17-slim

# 애플리케이션을 실행할 작업 디렉토리를 생성
WORKDIR /app

# 빌드 이미지에서 생성된 JAR 파일을 런타임 이미지로 복사
COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]

