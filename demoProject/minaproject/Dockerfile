FROM java:8

EXPOSE 9999

VOLUME /tmp

ADD target/minaproject-1.0.0-SNAPSHOT.jar /minaproject.jar

RUN bash -c 'touch /minaproject.jar'

#RUN apt-get install -y iputils-ping

#RUN apt-get install -y net-tools

ENTRYPOINT ["java", "-jar","/minaproject.jar"]




