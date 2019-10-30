##es Spring boot 整合elesticsearch报错  解决思路

报错类型
`报错None of the configured nodes are available`
云服务部署的es版本 为5.6.16 
 

```xml
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
         <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-elasticsearch</artifactId>
        </dependency>
```
配置
spring.data.elasticsearch.cluster-name=elasticsearch
spring.data.elasticsearch.cluster-nodes=129.28.189.234:9300
spring.data.elasticsearch.repositories.enabled=true

通过http 的方式是 直接访问9200端口是可以访问的`http://129.28.189.234:9200` 显示内容为
```json
{
    "name": "VQ8kJxh",
    "cluster_name": "elasticsearch",
    "cluster_uuid": "Gcrn0TsYStW_jNl6fsxdAQ",
    "version": {
        "number": "5.6.16",
        "build_hash": "3a740d1",
        "build_date": "2019-03-13T15:33:36.565Z",
        "build_snapshot": false,
        "lucene_version": "6.6.1"
    },
    "tagline": "You Know, for Search"
}
```

 通过Spring boot 连接 `http://localhost:8080/getById/1(写了一个Controller 这里省略掉)` 就报错
 报错内容
```json
{
    "code": 400,
    "message": "None of the configured nodes are available: [{#transport#-1}{jAgsg2vVSxSTzR4vbztiMA}{129.28.189.234}{129.28.189.234:9300}]"
}
```
期间单独写一个测试类 是这样的

```java

public class Client {

    public static void main(String[] args) {
        Settings settings = Settings.EMPTY;
        TransportClient client = null;
        try {
             client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("129.28.189.234"), 9300));
            GetResponse documentFields = client.prepareGet("website", "blog", "123").execute().actionGet();
            System.out.println(documentFields.getSourceAsString());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }finally {
            if (client != null) {
                client.close();
            }

        }
    }
}

```

发现这个类没有找到 `InetSocketTransportAddress` 大概猜到 是版本的问题 


网上搜索 `https://github.com/spring-projects/spring-data-elasticsearch/` 发现

Spring-data-elesticsearch版本与 elesticsearch 有一定的关系 关系如下
```
=====
|Spring Data Elasticsearch |Elasticsearch

|3.2.x |6.7.2
|3.1.x |6.2.2
|3.0.x |5.5.0
|2.1.x |2.4.0
|2.0.x |2.2.0
|1.3.x |1.5.2
======
```
spring boot 2.1.6 中引入的Spring-data-elasticsearch版本是`3.1.9.RELEASE` 所以对应的es版本是和我服务器上的版本不一致的 `顺便说一句，es7 es67 es5  的语法以及使用会稍稍有一些不一样`  排除原有的和es相关的jar包，引入正确的jar包

解决方式

```xml
<!--        统一使用 5.6.16 这个版本的elesticsearch 版本不一致，会出问题-->
       <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-elasticsearch</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.elasticsearch.plugin</groupId>
                    <artifactId>transport-netty4-client</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.elasticsearch.client</groupId>
                    <artifactId>transport</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--        统一使用 5.6.16 这个版本的elesticsearch   start-->
        <dependency>
            <groupId>org.elasticsearch</groupId>
            <artifactId>elasticsearch</artifactId>
            <version>5.6.16</version>
        </dependency>
        <dependency>
            <groupId>org.elasticsearch.plugin</groupId>
            <artifactId>transport-netty4-client</artifactId>
            <version>5.6.16</version>
        </dependency>
        <dependency>
            <groupId>org.elasticsearch.client</groupId>
            <artifactId>transport</artifactId>
            <version>5.6.16</version>
        </dependency>
        <!--        统一使用 5.6.16 这个版本的elesticsearch   end-->
```
再连 发现还是这个问题  woca    。。。。。。。

期间在本地开了一个 es 服务，  是可以的 连接的，但是服务器上就不行了


然后想起来昨天配置服务器 的时候将 9200 端口对外放开了，这个时候查看服务器防火墙
 ```
 sudo ufw status
 ```
显示

```
Status: active

To                         Action      From
--                         ------      ----
9200                       ALLOW       Anywhere                  
5601                       ALLOW       Anywhere                  
3306                       ALLOW       Anywhere                  
22                         ALLOW       Anywhere                  
80                         ALLOW       Anywhere                  
9200 (v6)                  ALLOW       Anywhere (v6)             
5601 (v6)                  ALLOW       Anywhere (v6)             
3306 (v6)                  ALLOW       Anywhere (v6)             
22 (v6)                    ALLOW       Anywhere (v6)             
80 (v6)                    ALLOW       Anywhere (v6)      
```
这就知道问题了  9300 没有放开，好吧，要么直接放开9300 要么直接把防火墙关掉

我选择的是直接关掉防火墙（公司环境不建议这么操作）

然后在重启Spring boot `http://localhost:8080/getById/123` 好的  就直接给我吧结果显示出来了

```json
{
    "message": "success",
    "code": 200,
    "result": {
        "id": "123",
        "firstName": null,
        "lastName": null,
        "age": 0,
        "about": null
    }
}
```
最后附上自己的elesticsearch.yml文件  网上有的地方说这里的`network.host` 配置不能是0.0.0.0 要配置本机ip, 其实在这里配置成本机ip 和0.0.0.0 都是一样的

```
#network.host: 172.30.0.8
network.host: 0.0.0.0 
http.port: 9200

```
总结以及教训：

```
这种每个版本之间区别很大的中间件，在使用jar包的时候一定保持版本统一

```


