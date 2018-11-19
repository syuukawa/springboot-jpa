### https://blog.csdn.net/runner1920/article/details/79035491
FROM java:8

VOLUME /tmp

ADD springboot-jpa-0.0.1.jar app.jar

RUN bash -c 'touch /app.jar'


###注意引号的问题
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]