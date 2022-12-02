FROM ubuntu:18.04
LABEL maintainer="louis"
# WORKDIR /app

RUN apt-get update  


RUN apt-get install -y vim  \
&& apt-get install net-tools \
&& apt-get install -y iputils-ping \
&& apt-get install -y openssh-* \
&& apt-get install -y nginx 

RUN apt-get install  -y rpm   \
&& apt-get install -y curl \
&& apt-get install -y  openjdk-8-jdk

# ENTRYPOINT [ "/etc/init.d/ssh",""start"]

ENTRYPOINT [ "/usr/sbin/nginx","-g","daemon off;" ]

EXPOSE 3000
EXPOSE 35729
EXPOSE 9090:8080
EXPOSE 80
EXPOSE 443
EXPOSE 22

# RUN apt-get install -y gcc  \
# && apt-get install -y automake  \
# && apt-get install -y autoconf  \
# && apt-get install -y libtool  \
# && apt-get install -y make  \
# && apt-get install -y make-doc  


# RUN apt-get install lsof  \
# && apt-get install htop  \
# && apt-get install -y wget  

