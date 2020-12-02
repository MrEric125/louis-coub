package com.louis.db.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Louis
 * @date created on 2020/12/2
 * description:
 */
@Repository
public interface InventoryRepository extends MongoRepository {
}
