package com.louis.bootmybatis.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author louis
 * <p>
 * Date: 2019/7/9
 * Description:
 */
@Repository
public interface BlogRepository extends ElasticsearchRepository<BlogInfo, String> {

}
