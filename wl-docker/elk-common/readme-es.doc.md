### dynamic mapping 
```json
{
  "mappings": {
    "doc_strict":{
      "dynamic":"strict",
      "properties":{
        "title":{"type":"text"},
        "stash":{"type":"text"}
      }
    }
  }
}
```
上面创建 mapping的时候指定了 dynamic 为strict,则不会动态新增field,当es用作与mysql数据同步的时候，则不能使用这个字段（说不准什么时候，mysql就加了个字段导致同步失败）