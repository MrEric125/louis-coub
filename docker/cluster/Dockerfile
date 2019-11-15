FROM redis

#将自定义文件拷入
COPY redis.conf /usr/local/etc/redi/redis.conf

RUN chmod /usr/local/etc/redis/redis.conf

EXPOSE 6061

# 集群总线端口:redis客户端连接的端口 + 10000
EXPOSE 16061

#使用自定义conf启动
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]