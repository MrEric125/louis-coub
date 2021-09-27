package louis.coub.escustomer.service;


import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * https://www.cnblogs.com/heyouxin/p/13865293.html
 */
@Service
public class AggregationRestHighLevelService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void a() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("batchId").field("batchId.keyword")
                .subAggregation(AggregationBuilders.sum("score").field("integral"))
                .subAggregation(AggregationBuilders.topHits("details").size(1));
        searchSourceBuilder.aggregation(aggregation);

        searchSourceBuilder.query(QueryBuilders.boolQuery());

        SearchRequest searchRequest = new SearchRequest("table");

        searchRequest.source(searchSourceBuilder);

        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

    }

}
