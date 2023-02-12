#!/bin/bash

# 构建标准包

# wget https://archive.apache.org/dist/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz

tar -zxvf zookeeper-3.4.14.tar.gz


cd zookeeper-3.4.14/conf/

cp zoo_sample.cfg zoo.cfg

cfg_name=zoo.cfg
data_dir=/apps/srv/instance/zookeeper-3.4.14/dataDir
data_log_dir=/apps/srv/instance/zookeeper-3.4.14/dataLogDir
sed -i 's/^dataDir/#dataDir/g' $cfg_name

echo dataDir=$data_dir >> $cfg_name
echo dataLogDir=$data_log_dir >> $cfg_name
echo server.1=server1:2888:3888 >> $cfg_name
echo server.2=server2:2888:3888 >> $cfg_name
echo server.3=server3:2888:3888 >> $cfg_name

 if [[ ! -e $data_dir ]]; then
        mkdir -p data_dir
 fi

cd $data_dir
if [[ ! -e myid ]]; then
        touch myid
 fi
echo 1 >> myid
cd ../
sh bin/zkServer.sh start

# 不同服务器，修改配置文件:

# 分发服务器

# 启动脚本