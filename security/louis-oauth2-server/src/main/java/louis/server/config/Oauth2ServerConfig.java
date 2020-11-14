package louis.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig  extends AuthorizationServerConfigurerAdapter {
}
