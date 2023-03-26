1.生成文档
    clone : docker run --name repo alpine/git clone https://github.com/docker/getting-started.ght
            docker cp repo:/git/getting-started/ .
    build :cd getting-started docker build -t docker101tutorial .
    run: docker run -d -p 80:80 --name docker-tutorial docker101tutorial
    查看文档： localhost


启动了一台docker 容器，但是有时候宿主机根本连不上 都ping不通？为什么？

这个原因主要还是因为docker 上的网络模型是基于桥接模式，会现在mac上创建一个linux的虚拟机，mac 与 window上会出现的问题
http://www.manongjc.com/detail/19-dlvjtpucmrwwhzj.html  
https://www.cnblogs.com/lucky9322/p/13648282.html

docker 删除虚悬镜像

docker rmi $(docker images -q -f dangling=true)

docker run -it -d --restart always --net host --cap-add NET_ADMIN --name connector wenjunxiao/mac-docker-connector
