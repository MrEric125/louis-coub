logstash文档理解

Hello world

bin/logstash -e "input { stdin { } } output { stdout {} }"

-e 指定一个字符串作为配置，格式和配置文件中的格式一样
如果什么都没有指定那么就会有一个默认的 默认的配置为
input { stdin { type=>stdin } } output { stdout {codec=>rubydebug} }

接下来我们在命令号输入`hello world` 就会有如下的输出
{
    "@timestamp" => 2019-09-20T08:51:15.728Z,
          "host" => "SF0001375950B",
      "@version" => "1",
       "message" => "hello world\r"
}


logstash 中 pipleline 配置规则分为三方面
input   filter   output

定义一个pipleline.conf  然后启动logstash的时候默认使用这个配置

查看配置文件格式是否正确
bin/logstash -f first-pipeline.conf --config.test_and_exit

使用配置文件启动
bin/logstash -f first-pipeline.conf --config.test_and_exit


使用fileBeat 将日志文件输入到logstash, 然后logstash 将日志文件输出到elasticsearch，这是最基本的操作


filebeat中有一个filebeat.yml配置文件  配置格式如下

每一个配置的含义是什么？？？
```yaml
    filebeat.prospectors:
    - type: log
      paths:
        - /path/to/file/logstash-tutorial.log 
    output.logstash:
      hosts: ["localhost:5044"]
```
启动filebeat
./filebeat -e -c filebeat.yml -d "publish"
以上配置含义：
 -e 启动的时候输出日志，但是不保存日志，
 -c 指定配置文件地址
 
 
 这个时候filebeat就会将指定路径中的文件收集起来 **好的那么问题来了，如何动态的将这个文件中的内容输入到logstash中呢？**
 在logstash中指定的first-pipeline.conf的配置格式如下
 
 
 ```conf
input {
    beats {
        port => "5044"
    }
}
 filter {
    grok {
        match => { "message" => "%{COMBINEDAPACHELOG}"}
    }
    geoip {
        source => "clientip"
    }
}
output {
    elasticsearch {
        hosts => [ "localhost:9200" ]
    }
}
```
 
 
  