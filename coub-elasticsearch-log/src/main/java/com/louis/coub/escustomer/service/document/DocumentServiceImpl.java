package com.louis.coub.escustomer.service.document;

import com.louis.coub.escustomer.exception.CommonError;
import com.louis.coub.escustomer.exception.Guarder;
import com.louis.coub.escustomer.model.SearchTypeEnums;
import com.louis.coub.escustomer.service.IndexServiceImpl;
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
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IndexServiceImpl indexService;


    public DocumentServiceImpl(RestHighLevelClient highLevelClient) {
        this.highLevelClient = highLevelClient;
    }

    /**
     * @param indexName
     * @param type
     * @param id
     * @param jsonStr
     * @return IndexResponse 这个就是http 形式返回的数据
     * @throws IOException
     */
    public IndexResponse addDocument(String indexName, String type, String jsonStr) throws IOException {
        IndexRequest request = new IndexRequest(indexName, type);
        request.source(jsonStr, XContentType.JSON);
        return indexService.insertIndexData(indexName, type, jsonStr);

    }

    public GetResponse getDocument(String indexName, String type, String id) throws IOException {
        GetRequest getRequest = new GetRequest(indexName, type, id);
        return highLevelClient.get(getRequest, RequestOptions.DEFAULT);
    }

    public SearchResponse queryAll(String index, String type) {
        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
        return query(index, type, () -> queryBuilder);


    }

    public SearchResponse boolQuery(String index, String type) {
        BoolQueryBuilder bool = (BoolQueryBuilder) this.queryBuilder(SearchTypeEnums.BOOL, null);
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("createTime", "123");
        bool.must(queryBuilder);
        Guarder<SearchResponse> guarder = Guarder.of(CommonError.INTERNAL_ERROR);

        return guarder.guard(() -> query(index, type, () -> bool));

    }


    /**
     * @param index           索引
     * @param type            type
     * @param builderSupplier 查询接口行为参数化
     * @return SearchResponse
     * @throws IOException
     */
    public SearchResponse query(@NotEmpty String index, @NotNull String type,
                                Supplier<QueryBuilder> builderSupplier) {

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

        Guarder<SearchResponse> guarder = Guarder.of(CommonError.INTERNAL_ERROR);
        return guarder.guard(() -> highLevelClient.search(searchRequest, RequestOptions.DEFAULT)
        );
    }

    public SearchResponse query(@NotEmpty String index, @NotNull String type,
                                Supplier<QueryBuilder> builderSupplier, Integer pageSize, Integer pageNum, String sortItem) {

        QueryBuilder queryBuilder = builderSupplier.get();
        if (Objects.isNull(queryBuilder)) {
            return null;
        }
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder);
        if (pageNum != null) {
            searchSourceBuilder.from(pageNum);
        }
        if (pageSize != null) {
            searchSourceBuilder.size(pageSize);
        }
        if (StringUtils.isNotBlank(sortItem)) {
            searchSourceBuilder.sort(sortItem, SortOrder.DESC);
        }
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        if (StringUtils.isNotBlank(type)) {
            searchRequest.types(type);
        }
        searchRequest.source(searchSourceBuilder);

        Guarder<SearchResponse> guarder = Guarder.of(CommonError.INTERNAL_ERROR);
        return guarder.guard(() -> highLevelClient.search(searchRequest, RequestOptions.DEFAULT)
        );
    }


    public SearchResponse queryPage(@NotEmpty String index, @NotNull String type,
                                    Supplier<QueryBuilder> builderSupplier, Integer pageSize, Integer pageNum, String sortItem) {
        return query(index, type, builderSupplier, pageSize, pageNum, sortItem);
    }

    public QueryBuilder queryBuilder(SearchTypeEnums queryType, Supplier<QueryBuilder> queryBuilders) {

        if (SearchTypeEnums.QUERY_ALL.equals(queryType)) {
            return QueryBuilders.matchAllQuery();
        } else if (SearchTypeEnums.BOOL.equals(queryType)) {
            return QueryBuilders.boolQuery();
        } else if (SearchTypeEnums.QUERY_STRING.equals(queryType)) {

            QueryBuilder queryBuilder = queryBuilders.get();
            if (queryBuilder != null) {
                return queryBuilder;
            }
            throw new NullPointerException("没有查询构造器");
        } else if (SearchTypeEnums.TERMS_QUERY.equals(queryType)) {
            QueryBuilder queryBuilder = queryBuilders.get();
            if (queryBuilder != null) {

                return queryBuilder;
            }
            throw new NullPointerException("没有查询构造器");
        }
        throw new NullPointerException("没有查询构造器");
    }

}
