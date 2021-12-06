package com.louis.coub.escustomer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.louis.common.common.HttpResult;
import com.louis.coub.escustomer.service.document.BuckServiceImpl;
import com.louis.coub.escustomer.service.document.DocumentServiceImpl;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @author jun.liu
 * @since 2021/2/4 13:43
 */
@RequestMapping("es")
@RestController
public class EsController {


    @Autowired
    private DocumentServiceImpl documentService;

    @Autowired
    private BuckServiceImpl buckService;


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询所有的索引
     * @return
     * @throws IOException
     */
    @RequestMapping("allIndex")
    public HttpResult getAllIndex() throws IOException {
        IndicesClient indices = restHighLevelClient.indices();
        GetAliasesRequest request = new GetAliasesRequest();
        GetAliasesResponse alias = indices.getAlias(request, RequestOptions.DEFAULT);
        Map<String, Set<AliasMetaData>> aliases = alias.getAliases();
        return HttpResult.ok(aliases);
    }


    /**
     * 插入数据
     * {
     *     "id": "123",
     *     "author":"author",
     *     "characters":["1","2","3"],
     *     "copies":3,
     *     "otitle":"otitle",
     *     "tags":["tags1","tags2","tags3"],
     *     "title":"title1",
     *     "year":2021,
     *     "available":true,
     *     "review":{
     *         "nickName":"nickName",
     *         "text":"text",
     *         "stars":3
     *     }
     * }
     * @param indexName
     * @param type
     * @param jsonStr
     * @return
     * @throws IOException
     */
    @RequestMapping("save")
    public HttpResult httpResult(String indexName,String type ,@RequestBody JSONObject jsonStr) throws IOException {
        String s = JSON.toJSONString(jsonStr);
        return HttpResult.ok(documentService.addDocument(indexName, type, s));
    }



    /**
     * 通过id获取数据
     * @param indexName
     * @param type
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("get")
    public HttpResult get(String indexName,String type , String id) throws IOException {
        return HttpResult.ok(documentService.getDocument(indexName, type, id));
    }


    /**
     * all 数据
     * @param index
     * @param type
     * @return
     */
    @RequestMapping("all")
    public HttpResult all(String index,String type) {
        return HttpResult.ok(documentService.queryAll(index, type));
    }

    /**
     * 批量操作
     * @param index
     * @param type
     * @return
     */
    @RequestMapping("buck")
    public HttpResult buck(String index, String type) {
        buckService.buckUpdate(index, type);
        return HttpResult.ok();
    }

}
