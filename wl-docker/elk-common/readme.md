## 集群启动问题列表
1. max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
解决方案：
```
    wsl -d docker-desktop
    sysctl -w vm.max_map_count=262144
```