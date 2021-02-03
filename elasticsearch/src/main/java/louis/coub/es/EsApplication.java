package louis.coub.es;


import com.louis.common.common.HttpResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableElasticsearchRepositories
public class EsApplication {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;




    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

    @RequestMapping("in")
    public HttpResult httpResult() {


        return HttpResult.ok("ok");
    }
}
