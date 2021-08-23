package louis.coub.esCustom;


import com.louis.common.common.HttpResult;
import louis.coub.esCustom.registry.EnableEsModel;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableElasticsearchRepositories
@EnableEsModel(entityPath = "louis.coub.es.model")
public class EsApplication {

    @Autowired
    private Client client;



    public static void main(String[] args) {
        SpringApplication.run(EsApplication.class, args);
    }

    @RequestMapping("in")
    public HttpResult httpResult() {
        Settings settings = client.settings();

        return HttpResult.ok(settings.toString());
    }
}
