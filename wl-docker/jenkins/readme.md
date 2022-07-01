sudo docker run -d --name lzl_jenkins -p 8080:8080 -p 50000:50000 -u 0 -v /usr/jenkins_home/:/var/jenkins_home jenkinsci/blueocean

docker pull jenkins:2.60.1