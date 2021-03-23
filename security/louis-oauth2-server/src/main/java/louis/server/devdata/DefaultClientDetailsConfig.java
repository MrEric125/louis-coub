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
import java.util.Optional;
import java.util.function.Supplier;

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
                grantType->{
                    Optional<GrantTypeEntity> typeEntity = grantTypeRepository.findOneByValue(grantType);

                    Supplier<GrantTypeEntity> supplier = () -> {
                        GrantTypeEntity grantTypeEntity = GrantTypeEntity.builder().value(grantType).build();

                        return grantTypeRepository.save(grantTypeEntity);
                    };

                    typeEntity.orElseGet(supplier);
                }
        );
//        保存scope
        Arrays.stream(DevDataAutoCreate.DEFAULT_SCOPES).forEach(
                scope -> {
                    Supplier<ScopeEntity> supplier = () -> {
                        ScopeEntity scopeEntity = ScopeEntity.builder().value(scope).build();
                        return scopeRepository.save(scopeEntity);
                    };
                    scopeRepository.findOneByValue(scope).orElseGet(supplier);
                }
        );


//        DevDataAutoCreate.loadBaseClientDetails().forEach(baseClientDetails -> {
//            clientDetailsRepository.findOneByClientId(baseClientDetails.getClientId()).orElseGet(()->clientDetailsRepository.save())
//        });




    }
}
