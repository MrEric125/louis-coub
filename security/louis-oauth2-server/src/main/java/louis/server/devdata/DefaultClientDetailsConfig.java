package louis.server.devdata;

import louis.server.entity.ClientDetailsEntity;
import louis.server.entity.GrantTypeEntity;
import louis.server.entity.ScopeEntity;
import louis.server.repository.ClientDetailsRepository;
import louis.server.repository.GrantTypeRepository;
import louis.server.repository.ScopeRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author louis
 * <p>
 * Date: 2019/11/28
 * Description:
 * 初始化的时候加载数据
 */
@Configuration
public class DefaultClientDetailsConfig implements InitializingBean {
    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Autowired
    private GrantTypeRepository grantTypeRepository;

    @Autowired
    private ScopeRepository scopeRepository;




    @Override

    public void afterPropertiesSet() throws Exception {

//        save grantType
        Arrays.stream(DevDataAutoCreate.DEFAULT_GRANT_TYPES).forEach(
                grantType-> grantTypeRepository.findOneByValue(grantType).orElseGet(
                        () -> grantTypeRepository.save(
                                GrantTypeEntity.builder().value(grantType).build()
                        )
                )
        );
//        保存scope
        Arrays.stream(DevDataAutoCreate.DEFAULT_SCOPES).forEach(
                scope -> scopeRepository.findOneByValue(scope).orElseGet(
                        () -> scopeRepository.save(
                                ScopeEntity.builder().value(scope).build()
                        )
                )
        );


//        DevDataAutoCreate.loadBaseClientDetails().forEach(baseClientDetails -> {
//            clientDetailsRepository.findOneByClientId(baseClientDetails.getClientId()).orElseGet(()->clientDetailsRepository.save())
//        });




    }
}
