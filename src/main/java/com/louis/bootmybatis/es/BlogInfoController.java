package com.louis.bootmybatis.es;

import com.louis.bootmybatis.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/7/9
 * Description:
 */
@RestController
public class BlogInfoController {

    @Autowired
    private BlogRepository blogRepository;

    @RequestMapping("/getById/{id}")
    public ResponseData getById(@PathVariable("id") String id) {

        System.out.println(aopTest());
        Optional<BlogInfo> blogInfo = blogRepository.findById(id);
        Iterable<BlogInfo> all = blogRepository.findAll();
        BlogInfo elseThrow = blogInfo.orElseThrow(() -> new NullPointerException("这个id没有找到实体"));
        return new ResponseData("success", 200, elseThrow + aopTest());
    }

    public String aopTest() {
        return "aopTest";
    }
}
