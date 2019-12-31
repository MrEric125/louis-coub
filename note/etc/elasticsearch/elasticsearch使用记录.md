
## index api 

```
PUT twitter/_doc/1
{
    "user" : "kimchy",
    "post_date" : "2009-11-15T14:12:12",
    "message" : "trying out Elasticsearch"
}
```
响应为：
```
{
  "_index" : "twitter",
  "_type" : "_doc",
  "_id" : "1",
  "_version" : 1,
  "result" : "created",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}

```
**注意**    
    Automatic index creation is controlled by the action.auto_create_index setting. This setting defaults to true, meaning that indices are always automatically created

```    
PUT _cluster/settings
{
    "persistent": {
        "action.auto_create_index": "twitter,index10,-index1*,+ind*" 
    }
}

PUT _cluster/settings
{
    "persistent": {
        "action.auto_create_index": "false" 
    }
}

PUT _cluster/settings
{
    "persistent": {
        "action.auto_create_index": "true" 
    }
}
```
## 创建mapping
```console
PUT twitter
{
   "mappings": {
      "_doc": {
         "properties": {
            "counter": {
               "type": "integer",
               "store": false
            },
            "tags": {
               "type": "keyword",
               "store": true
            }
         }
      }
   }
}
```

**删除操作**    
```
删除当前document
DELETE twitter/_doc/1
删除所有的index
delete wtitter 
```

* delete by query api
```console
POST twitter/_delete_by_query
{
  "query": { 
    "match": {
      "message": "some message"
    }
  }
}
```
## query 
1. queryString    
   en 详细参数讲解 见 https://www.elastic.co/guide/en/elasticsearch/reference/6.8/search-uri-request.html   
   cn(版本只是2.0) https://www.elastic.co/guide/cn/elasticsearch/guide/current/_finding_exact_values.html


2. DSL
* matchall
```
GET /blog/article/_search
{
  "query": {
    "match_all": {}
  }
}
```

一般查找 es 会有一个评分的过程，但是当我们使用精确查询的时候，我们不需要这个评分的过程（有就返回数据，没有就算了），我们会使用过滤器（filters）过滤器很重要，因为它们执行速度非常快，不会计算相关度（直接跳过了整个评分阶段）而且很容易被缓存。
```console
GET /_search
{
  "query": {
    "constant_score": {
      "filter": {
        "term": {
          "price": "30"
        }
      }
      
    }
  }
}
```
使用`constant_score` 将`term`查询转化为过滤器

```
GET _search
{
  "query": {
    "constant_score": {
      "filter": {
        "term": {
          "productID": "XHDK-A-1293-#fJ3"
        }
      }
      
    }
  }
}
```
通过以上方式来精确查找，可能找不到我们想要的值，即使它已经存在,试试以下的你就知道为什么
```
GET /my_store/_analyze
{
  "field": "productID",
  "text": "XHDK-A-1293-#fJ3"
}
```
索引数据的方式，`elasticsearch` 默认使用`analyze` 
所以我们需要重新更改mapping信息
```console
# 这一段是必须的，elasticsearch无法修改mapping信息，只能新建
DELETE /my_store 

PUT /my_store 
{
    "mappings" : {
        "products" : {
            "properties" : {
                "productID" : {
                    "type" : "string",
                    "index" : "not_analyzed" 
                }
            }
        }
    }

}
```
### 过滤器
  






**todo**  
在我们查询的时候，有时候会有分页，有时候会有排序，这些条件是在哪里设置

query中的bool  和boosting indices_boost  以及boost的功能点各是什么，如何使用

elasticsearch 6.8如何更新mapping信息
