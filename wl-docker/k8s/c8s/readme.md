### docker 中自带的k8s(kubeadmin)  配置kubernetes-dashboard

https://artifacthub.io/packages/helm/k8s-dashboard/kubernetes-dashboard?modal=install

运行： 
 kubectl -n kubernetes-dashboard port-forward svc/kubernetes-dashboard-kong-proxy 8443:443
 创建用户参考
 https://github.com/kubernetes/dashboard/blob/master/docs/user/access-control/creating-sample-user.md

访问： https://localhost:8443/

卸载： helm uninstall kubernetes-dashboard
 
 文档参考：
 https://kubernetes.io/zh-cn/docs/tasks/access-application-cluster/web-ui-dashboard/
