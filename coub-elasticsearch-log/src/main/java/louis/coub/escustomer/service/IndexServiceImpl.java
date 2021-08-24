package louis.coub.escustomer.service;

import lombok.extern.slf4j.Slf4j;
import louis.coub.escustomer.config.EsProperties;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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

    public void createIndex(String index, String json) {
        CreateIndexRequest createIndexRequest = Requests.createIndexRequest(index);
        createIndexRequest.source(json, XContentType.JSON);
        try {
            indicesClient.create(createIndexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
