passwd root
apt-get install -y rpm
apt-get install -y openjdk-8-jdk
apt install bash-completion
service ssh start
<!-- 解决docker网络互通问题： https://blog.csdn.net/tqtaylor/article/details/119799526?spm=1001.2101.3001.6650.1&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-119799526-blog-117808949.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1-119799526-blog-117808949.pc_relevant_default&utm_relevant_index=2 -->

https://github.com/wenjunxiao/mac-docker-connector

ansible  : 自动化运维

## docker 容器互信

1. 本地启动docker-connector
   会导致 本地服务连不上vpns

```
   sudo brew services start docker-connector
```

1. 本地启动桥接容器

```
 docker run -it -d --restart always --net host --cap-add NET_ADMIN --name connector wenjunxiao/mac-docker-connector
```

3. 容器运行ssh

```
service ssh start
```

4. 宿主机与docker 容器互信

```
ssh-copy-id -i ~/.ssh/id_rsa.pub node1
```

## 部署的原理

1. 创建项目目录
2. 发送jar包
3. 生成 restart.sh 文件
4. 生成存放tmp 文件，log日志目录
5. 执行restart.sh 启动应用

