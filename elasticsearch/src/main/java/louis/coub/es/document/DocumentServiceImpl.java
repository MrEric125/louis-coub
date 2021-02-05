package louis.coub.es.document;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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

    public SearchResponse queryAll(String index,String type) throws IOException {

        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index).types(type);
        searchRequest.source(searchSourceBuilder);
        return highLevelClient.search(searchRequest, RequestOptions.DEFAULT);


    }

    public SearchResponse boolQuery(String index,String type) throws IOException {
        BoolQueryBuilder bool = (BoolQueryBuilder) this.queryBuilder("bool");


        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(bool);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index).types(type);
        searchRequest.source(searchSourceBuilder);
        return highLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    }

    public QueryBuilder queryBuilder(String queryType) {

        if (queryType.equals("queryAll")) {
            return QueryBuilders.matchAllQuery();
        } else if (queryType.equals("bool")) {
            return QueryBuilders.boolQuery();
        }
        return QueryBuilders.matchAllQuery();
    }

}
