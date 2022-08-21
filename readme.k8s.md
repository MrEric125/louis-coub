

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
6. 存储
7. 调度器
8. 安全
   1. 集群认证
   2. 鉴权
   3. 访问控制器
9. HELM yum 包管理器
10. 