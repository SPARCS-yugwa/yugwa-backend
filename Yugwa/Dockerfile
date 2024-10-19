# 1. OpenJDK 17 Alpine 이미지 사용
FROM openjdk:17-alpine
# 2. 타임존 설정
RUN apk add --no-cache tzdata
# 3. JAR 파일을 인자로 받음
ARG JAR_FILE=build/libs/Yugwa-0.0.1-SNAPSHOT.jar
# 4. JAR 파일을 컨테이너의 /app.jar로 복사
COPY ${JAR_FILE} /app.jar
# 5. 컨테이너 시작 시 스프링 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]