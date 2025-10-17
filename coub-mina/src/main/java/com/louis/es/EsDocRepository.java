package com.louis.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsDocRepository extends ElasticsearchRepository<EsDoc, String> {
}
