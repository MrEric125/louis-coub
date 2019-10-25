package com.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author louis
 * <p>
 * Date: 2019/10/23
 * Description:
 */
@Repository
public interface CompanyRepository extends ElasticsearchRepository<CompanyDocument,Long> {

//    @Query("{\n" +
//            "  \"query\": {\n" +
//            "    \"query_string\": {\n" +
//            "      \"default_field\": \"companyCode\",\n" +
//            "      \"query\": \"?0\"\n" +
//            "    }\n" +
//            "  }\n" +
//            "}")
//    CompanyDocument searchByCompanyCode(String companyCode);


}
