package com.louis.db.mongo;

import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author Louis
 * @date created on 2020/12/2
 * description:
 */
@RestController
public class MongoController {

    @Autowired
    private InventoryRepository inventoryRepository;


    @RequestMapping("/mongoResult")
    public HttpResult mongoResult(String search) {
        Inventory inventory = new Inventory();
        inventory.setItem(search);

        List all = inventoryRepository.findAll(Example.of(inventory));
        return HttpResult.ok(all);
    }
}
