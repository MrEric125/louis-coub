FROM java:8
# 维护者信息
MAINTAINER louis@
# 这里的 /tmp 目录就会在运行时自动挂载为匿名卷，任何向 /tmp 中写入的信息都不会记录进容器存储层
VOLUME /tmp
# 复制上下文目录下的 /build/libs/fastboot-0.0.1.jar 到容器里
#$ log.infoan install -Dmaven.test.skip=true -pl distribute/rpc-link/dubbo-source-producer -am
COPY /target/dubbo-source-producer.jar dubbo-source-producer.jar
# bash方式执行，使fastboot-0.0.1.jar可访问
# RUN新建立一层，在其上执行这些命令，执行结束后， commit 这一层的修改，构成新的镜像。
RUN bash -c "touch /dubbo-source-producer.jar"
# 指定时区
# ENV TZ='Asia/Shanghai'
# 声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务
EXPOSE 10007
# 指定容器启动程序及参数   <ENTRYPOINT> "<CMD>"
ENTRYPOINT ["java","-jar","dubbo-source-producer.jar", "--spring.profiles.active=prod"]
