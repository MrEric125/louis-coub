mvn -v
#mvn clean package -Dmaven.test.skip=true -pl demoProject/minaproject -am
docker version
docker build -t 'docker-experiece' .
containerId=$(docker ps -aq -f  'name=docker-experience')
if [ ${containerId} ]; then
  echo "容器已经存在，先删除"
  docker rm -f ${containerId}
fi
docker run -d -p 10088:10088 --name docker-experience -it docker-experiece  /bin/bash

echo "启动成功"
