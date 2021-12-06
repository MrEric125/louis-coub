package com.louis.coub.escustomer.service;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static org.elasticsearch.client.Requests.deleteIndexRequest;

/**
 * @author jun.liu
 * @since 2021/2/4 10:37
 */
@Slf4j
@Service
public class IndexServiceImpl {

    private final RestHighLevelClient restHighLevelClient;

    private final IndicesClient indicesClient;


    public IndexServiceImpl(RestHighLevelClient client) {
        this.restHighLevelClient = client;
        this.indicesClient = this.restHighLevelClient.indices();
    }

    /**
     * 通过mapping 创建index
     * @param index 索引名
     * @param json mapping 配置
     */
    public void createIndex(String index, String json) {
        CreateIndexRequest createIndexRequest = Requests.createIndexRequest(index);
        createIndexRequest.source(json, XContentType.JSON);
        try {
            indicesClient.create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过索引id 和type创建index
     * @param index 索引名
     * @param type type名
     * @param json 保存数据
     */
    public IndexResponse insertIndexData(String index, String type, String json) throws IOException {
        IndexRequest request = new IndexRequest(index, type);
        request.source(json, XContentType.JSON);
        return restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }

    public boolean existIndex(String index) throws IOException {
            return indicesClient.exists(new GetIndexRequest().indices(index), RequestOptions.DEFAULT);

    }
    public void deleteIndex(String index) {
        DeleteIndexRequest indexRequest = deleteIndexRequest(index);
        try {
            indicesClient.delete(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
