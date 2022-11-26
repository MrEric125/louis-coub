
## docker 启动 k8s

## 后台启动启动代理

```bash 
nohup kubectl proxy  >/dev/null 2>&1 & 
```
启动代理后就可以在页面（localhost:8001）看到k8s 相关配置

### 安装 kuboard 


kubectl apply -f https://addons.kuboard.cn/kuboard/kuboard-v3.yaml

kubectl get pods -n kuboard（这里需要多等一会）

可能直接拉取不下来 可以手动拉取，docker pull eipwork/kuboard:v3.5.0.3

登录页面： 宿主机host:30080
默认账号密码  admin/Kuboard123   
如果密码不对，可以 docker exec -it kuboard-etcd 中，执行`kuboard-admin reset-password`


### 核心概念 && 架构

master  
 一些计算节点，作为管理节点，也就是master,其他就是工作节点，也就是node节点
master 上有一些管理组件
   1. api server 
      1. api 网关职责，用户与其他系统 与k8s 交互的唯一入口，提供了各类资源对象CRUD 的http 接口，整个集群的持久化数据，都是由api server 处理后保存到etcd 中的，
   2. scheduler
      1. 调度器
   3. controller manager
      1. 控制中心，
   4. etcd
      1. 
node 
    kubelet 
    容器引擎
    插件




1. service 

