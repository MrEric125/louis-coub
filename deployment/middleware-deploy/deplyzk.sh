#!/bin/bash


base_dir=/apps/srv/instance
zk_dir=/zookeeper-3.4.14
cfg_name=$base_dir$zk_dir/conf/zoo.cfg
data_dir="$base_dir$zk_dir"/dataDir
data_log_dir="$base_dir$zk_dir"/dataLogDir
host_cfg_name=/etc/hosts
zkHosts=()

# 宿主机构建标准包
if [[ ! -e $base_dir$zk_dir.tar.gz ]]; then
   cd $base_dir
   wget https://archive.apache.org/dist/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz
else
    echo "$base_dir$zk_dir.tar.gz exist"
fi

if [[ ! -e $base_dir$zk_dir ]]; then
    cd $base_dir
    tar -zxvf zookeeper-3.4.14.tar.gz
else
    echo "$base_dir$zk_dir exist"
fi



cp $base_dir$zk_dir"/conf/zoo_sample.cfg" $cfg_name

# 注释原有dataDir开头的配置
sed -i 's/^dataDir/#dataDir/g' $cfg_name

# 新的配置地址
echo "dataDir=$data_dir" >> $cfg_name
echo "dataLogDir=$data_log_dir" >> $cfg_name


# 获取服务器地址
while getopts ":h:" opt
do
    case $opt in
        h)
          zkHosts=(${OPTARG//,/ })
          echo "集群ip为: $OPTARG"

          for((i=0;i<${#zkHosts[@]};i++)); do
            echo $data_dir
            scp -r $base_dir$zk_dir roo@$zkHosts[i]:$base_dir
            ssh ${zkHosts[i]} "mkdir -p $data_dir;"
            declare -i myid=$i+1
            ssh ${zkHosts[i]} "touch $data_dir/myid | echo $myid >> $data_dir/myid"
            ssh ${zkHosts[i]} "cd '$base_dir$zk_dir' | ./bin/zkServer.sh start conf/zoo.cfg"
          done
        ;;

        ?)
          echo "未知参数"
        exit 1;;
    esac
done


# 执行部署逻辑
function deploy()
{
    echo $data_dir
    if [[ ! -e $data_dir ]]; then
      mkdir -p $data_dir
    fi
    cd $data_dir
    if [[ ! -e myid ]]; then
        touch myid
        declare -i myid=$i+1
        echo $myid >> myid
    fi

    cd "$base_dir$zk_dir"
    ./bin/zkServer.sh start conf/zoo.cfg
}

function usage(){
    exit 1
}
