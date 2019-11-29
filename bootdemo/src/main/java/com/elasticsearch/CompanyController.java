//package com.elasticsearch;
//
//import com.google.common.collect.Lists;
//import com.louis.common.common.WrapMapper;
//import com.louis.common.common.Wrapper;
//import org.elasticsearch.index.query.MatchAllQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.data.util.CloseableIterator;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author louis
// * <p>
// * Date: 2019/10/23
// * Description:
// */
//@RestController
//@RequestMapping("company")
//public class CompanyController {
//
//    @Autowired
//    private CompanyRepository companyRepository;
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    @RequestMapping("save")
//    public Wrapper saveCompany(@RequestBody CompanyDocument company) {
//        CompanyDocument save = companyRepository.save(company);
//        return WrapMapper.ok(save);
//
//    }
//
//    @RequestMapping("search/{companyCode}")
//    public Wrapper search(@PathVariable String companyCode) {
//        MatchAllQueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(queryBuilder)
//                .withPageable(PageRequest.of(0, 10))
//                .build();
//
//        CloseableIterator<CompanyDocument> stream = elasticsearchTemplate.stream(searchQuery, CompanyDocument.class);
//        List<CompanyDocument> documentList = Lists.newArrayList();
//        while (stream.hasNext()) {
//            documentList.add(stream.next());
//        }
//
//        return WrapMapper.ok(documentList);
//    }
//
//}
