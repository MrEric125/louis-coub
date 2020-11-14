1. 运行 elasticsearch时候报： docker max virtual memory areas vm.max_map_count [65530] is too low, increase to at
这个其实就是 es需要配置更大的内存 
目前没有找到修改镜像配置的方法，只能进入容器修改配置，但是容器又进不去，真是一个矛盾的问题

主要要修改的就是docker 中的虚拟机参数，现在没有进入docker 虚拟机的参数

找到了
https://github.com/docker/for-win/issues/5202

```open powershell
   wsl -d docker-desktop
   sysctl -w vm.max_map_count=262144
```

时间，、
 看来自己需要好好提高一下关于linux  网络相关方面知识