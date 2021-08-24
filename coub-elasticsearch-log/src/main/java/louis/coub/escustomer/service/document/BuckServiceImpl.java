package louis.coub.escustomer.service.document;

import lombok.extern.slf4j.Slf4j;
import louis.coub.escustomer.model.SearchTypeEnums;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class BuckServiceImpl {

    @Autowired
    private  RestHighLevelClient highLevelClient;

    @Autowired
    private DocumentServiceImpl documentService;

//    @Autowired
//    private Client client;


    /**
     * 执行逻辑：
     * 查找es中最新的数据id,然后通过批量查询数据库中大于这个id的数据，然后更新
     * @param index
     * @param type
     */
    public void buckUpdate(String index, String type) {
        QueryBuilder queryBuilder = documentService.queryBuilder(SearchTypeEnums.QUERY_ALL, null);

        SearchResponse searchResponse = documentService.queryPage(index, type, () -> queryBuilder, 1, 0, "id.keyword");
        SearchHit[] hits = searchResponse.getHits().getHits();

        if (Arrays.stream(hits).count()<=0) {
            return;
        }
        int id = hits[0].docId();




    }
}
