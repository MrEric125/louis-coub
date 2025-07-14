FROM java:8

EXPOSE 9999

VOLUME /tmp

ADD target/minaproject-1.0.0-SNAPSHOT.jar /minaproject.jar

RUN bash -c 'touch /minaproject.jar'

ENTRYPOINT ["java", "-jar","/minaproject.jar"]




