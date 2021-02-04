package louis.coub.es.document;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author jun.liu
 * @since 2021/2/4 13:37
 */
@Service
public class DocumentServiceImpl {

    @Autowired
    private RestHighLevelClient highLevelClient;


    @Autowired
    private ElasticsearchOperations elasticsearchOperations;


    /**
     *
     * @param indexName
     * @param type
     * @param id
     * @param jsonStr
     * @return IndexResponse 这个就是http 形式返回的数据
     * @throws IOException
     */
    public IndexResponse addDocument(String indexName,String type ,String id,String jsonStr) throws IOException {
        IndexRequest request = new IndexRequest(indexName, type, id);

        request.source(jsonStr, XContentType.JSON);

        return highLevelClient.index(request, RequestOptions.DEFAULT);
    }

    public GetResponse getDocument(String indexName,String type ,String id) throws IOException {
        GetRequest getRequest = new GetRequest( indexName, type , id);

         return highLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

}
