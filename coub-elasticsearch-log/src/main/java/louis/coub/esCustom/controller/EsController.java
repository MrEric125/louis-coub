package louis.coub.esCustom.controller;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import louis.coub.esCustom.document.BuckServiceImpl;
import louis.coub.esCustom.document.DocumentServiceImpl;
import louis.coub.esCustom.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author jun.liu
 * @since 2021/2/4 13:43
 */
@RequestMapping("es")
@RestController
public class EsController {


    @Autowired
    private DocumentServiceImpl documentService;

    @Autowired
    private BuckServiceImpl buckService;


    @RequestMapping("save")
    public HttpResult httpResult(String indexName,String type ,@RequestBody BookEntity jsonStr) throws IOException {
        String s = JSON.toJSONString(jsonStr);

        return HttpResult.ok(documentService.addDocument(indexName, type, jsonStr.getId(), s));
    }

    @RequestMapping("get")
    public HttpResult get(String indexName,String type , String id) throws IOException {
        return HttpResult.ok(documentService.getDocument(indexName, type, id));
    }


    @RequestMapping("all")
    public HttpResult all(String index,String type) {
        return HttpResult.ok(documentService.queryAll(index, type));
    }

    @RequestMapping("buck")
    public HttpResult buck(String index, String type) {
        buckService.buckUpdate(index, type);
        return HttpResult.ok();
    }

}
