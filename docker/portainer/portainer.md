portainer 页面管理启动尝试

 docker pull portainer/portainer
 
docker volume create portainer_data

docker run -d -p 9000:9000 -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data portainer/portainer

docker 