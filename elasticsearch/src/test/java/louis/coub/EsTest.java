package louis.coub;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author louis
 * <p>
 * Date: 2019/10/23
 * Description:
 */
@Slf4j
public class EsTest {

    private Settings settings = Settings.builder().put("cluster.name","docker-cluster").build();

    private TransportClient client = null;
    private String INDEX = "library";
    private String TYPE = "book";

    private String ALL_INDEX = "_all";

    @Before
    public void before() throws UnknownHostException {
        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(
                        new TransportAddress(
                                InetAddress.getByName("localhost"),
                                9300));
    }
    @Test
    public void searchIds() {
        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("1");
        this.searchNoPage(queryBuilder, ALL_INDEX, ALL_INDEX);

    }

    private void searchNoPage(QueryBuilder queryBuilder,String index,String type) {
        this.search(queryBuilder, index,type,null, null);
    }

    private void search(QueryBuilder queryBuilder,String index,String type,Integer fromSize,Integer pageSize) {
        String field = queryBuilder.getName();
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field(field).preTags("<em>").postTags("<em>");


        SearchResponse response = client
                .prepareSearch(index)
                .setFrom(fromSize == null ? 0 : fromSize)
                .setSize(pageSize == null ? 10 : pageSize)
                .setQuery(queryBuilder)
                .highlighter(highlightBuilder)

                .get();
        SearchHits hits = response.getHits();
        log.info("查询总结果：{}", hits.getTotalHits());
        hits.forEach(x->{
            System.out.println(x.getSourceAsString());
//            Map<String, Object> source = x.getSource();
//            Map<String, HighlightField> highlightFields = x.getHighlightFields();
//            if (MapUtils.isNotEmpty(highlightFields)) {
//                System.out.println(highlightFields);
//            }
//            System.out.println(source);
        });
    }

}
