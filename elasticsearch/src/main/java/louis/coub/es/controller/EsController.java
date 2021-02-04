package louis.coub.es.controller;

import com.alibaba.fastjson.JSON;
import com.louis.common.common.HttpResult;
import louis.coub.es.document.DocumentServiceImpl;
import louis.coub.es.model.BookEntity;
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


    @RequestMapping("save")
    public HttpResult httpResult(String indexName,String type ,@RequestBody BookEntity jsonStr) throws IOException {
        String s = JSON.toJSONString(jsonStr);

        return HttpResult.ok(documentService.addDocument(indexName, type, jsonStr.getId(), s));
    }

    @RequestMapping("get")
    public HttpResult get(String indexName,String type , String id) throws IOException {
        return HttpResult.ok(documentService.getDocument(indexName, type, id));
    }



}
