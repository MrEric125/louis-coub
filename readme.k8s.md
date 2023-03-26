

1. pod : K8s 管理的最小单位
    1. 管理器管理的pod
       1. RS, RC
       2. deployment
       3. HPA
       4. statefullset
       5. daemonSet
       6. job,cronJonb
       7. 
    2. pod 协同
    3. 服务发现
    4. 控制器类型
2. 通讯模式：
    1. 组件同事模式

3. 资源清单
    1. 通过资源清单创建pod  
    2. **pod 生命周期**   
       1. initC 
       2. pod phase 
       3. 容器探针  
          1. livenessProbe  
          2. readinessProbe 
       4. pod hook  
       5. 重启策略  
 4. 控制器
    1. 不同控制器的特点，
 5. 服务发现(SVC)
    1. service原理
    2. service含义
    3. service常见分类
    4. clusterIP
    5. nodePort
    6. externalName
    7. server实现方式
       1. userspace
    8. 服务分类 
       1. 有状态服务，
       2. 无状态服务 
4. 存储(不同环境选择不同的存储器)
   1. configmap
   2. secret
   3. volume
      1. emptyDir
      2. hostPath
   4. PV
5. 调度器
   1. 调度过程
   2. 自定义调度器
   3. 调度亲和性
6. 安全（重要）
   1. 集群认证
   2. 鉴权
   3. 访问控制器
7. HELM yum 包管理器


### k8s 来源于borg 系统
20220822185551.jpg

### etcd
for  storage 
推荐在k8s集群中使用ectd v3, v2 是内存存储，在k8s v1.11 中已启用
### api server 
所有组件都需要通过api server 访问服务
### . kubelet (维持pod 的生命周期)
   直接跟容器引擎交互实现容器的生命周期管理
### . kube proxy 
   负责写入我们的规则至，IPtables 或者ipvs 实现服务映射访问
### pod

#### 自主式pod
#### 集群管理pod
statefulset： 为了解决状态服务的问题
   1. 稳定的持久化存储
   2. 稳定的网络标识，也就是pod 重新调度后，其hostname会保持不变
   3. 有序部署，有序扩展
   4. 有序收缩，有序删除
deamonset 确保全部node 上运行一个pod 副本
job ,cron job: 负责批处理任务，也就是执行一次的任务，



#### 网络访问方式

### controller manager 
维持副本期望数目，
### scheduler: 
 负责介绍任务，选择合适的节点进行分配任务
### coredns（实现负载均衡重要组件）
可以为集群中svc 创建一个域名的对应关系解析
### ingress controller 
官方为我们实现了四层代理，ingress 为我们实现了七层代理
### federation
提供一个可以跨集群中心，多k8s 统一管理功能
### prometheus
提供一个k8s 的监控能力
### elk 集群日志分析平台

## k8s 课件：11111111
https://github.com/spring2go/k8s-msa-in-action-ppt
https://github.com/spring2go/k8s-msa-in-action

## pod 端口转发
kubectl port-forward pod-name 8080(主机端口):8080(容器端口)

## 服务暴露 通过service 暴露pod 端口，方便外部访问（类似nginx 反向代理）

理解service 是步入k8s 大门的基础

