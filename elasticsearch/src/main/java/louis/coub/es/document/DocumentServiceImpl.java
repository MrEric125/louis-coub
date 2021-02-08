package louis.coub.es.document;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author jun.liu
 * @since 2021/2/4 13:37
 */
@Validated
@Service
public class DocumentServiceImpl {

    private final RestHighLevelClient highLevelClient;

    public DocumentServiceImpl(RestHighLevelClient highLevelClient) {
        this.highLevelClient = highLevelClient;
    }


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

        return query(index, type, () -> queryBuilder);


    }

    public SearchResponse boolQuery(String index,String type) throws IOException {
        BoolQueryBuilder bool = (BoolQueryBuilder) this.queryBuilder("bool");
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("createTime", "123");
        bool.must(queryBuilder);

        return query(index, type, () -> bool);

    }

    /**
     *
     * @param index 索引
     * @param type type
     * @param builderSupplier 查询接口行为参数化
     * @return SearchResponse
     * @throws IOException
     */
    public SearchResponse query(@NotEmpty String index, @NotNull String type, Supplier<QueryBuilder> builderSupplier)throws IOException  {

        QueryBuilder queryBuilder = builderSupplier.get();
        if (Objects.isNull(queryBuilder)) {
            return null;
        }
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        if (StringUtils.isNotBlank(type)) {
            searchRequest.types(type);
        }
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
