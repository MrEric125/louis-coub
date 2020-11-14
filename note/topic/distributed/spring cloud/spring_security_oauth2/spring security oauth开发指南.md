

### spring security 开发指南

在使用 spring-cloud的时候，一般的，关于权限认证这一块会使用Spring-security 和oauth2 结合起来

**接下来就是关于相关的开发指南**

#### Spring Oauth2 解决的问题：

我们假设你有一个“云笔记”产品，并提供了“云笔记服务”和“云相册服务”，此时用户需要在不同的设备（PC、Android、iPhone、TV、Watch）上去访问这些“资源”（笔记，图片）

那么用户如何才能访问属于自己的那部分资源呢？此时传统的做法就是提供自己的账号和密码给我们的“云笔记”，登录成功后就可以获取资源了。但这样的做法会有以下几个问题：

- “云笔记服务”和“云相册服务”会分别部署，难道我们要分别登录吗？
- 如果有第三方应用程序想要接入我们的“云笔记”，难道需要用户提供账号和密码给第三方应用程序，让他记录后再访问我们的资源吗？
- 用户如何限制第三方应用程序在我们“云笔记”的授权范围和使用期限？难道把所有资料都永久暴露给它吗？
- 如果用户修改了密码收回了权限，那么所有第三方应用程序会全部失效。
- 只要有一个接入的第三方应用程序遭到破解，那么用户的密码就会泄露，后果不堪设想。
为了解决如上问题，oAuth 应用而生。

#### Spring Oauth2 中关键名词 

1. 第三方应用程序（Third-party application）： 又称之为客户端（client），比如上节中提到的设备（PC、Android、iPhone、TV、Watch），我们会在这些设备中安装我们自己研发的 APP。又比如我们的产品想要使用 QQ、微信等第三方登录。对我们的产品来说，QQ、微信登录是第三方登录系统。我们又需要第三方登录系统的资源（头像、昵称等）。对于 QQ、微信等系统我们又是第三方应用程序。
2. HTTP 服务提供商（HTTP service）： 我们的云笔记产品以及 QQ、微信等都可以称之为“服务提供商”。
3. 资源所有者（Resource Owner）： 又称之为用户（user）。
4. 用户代理（User Agent）： 比如浏览器，代替用户去访问这些资源。
5. 认证服务器（Authorization server）： 即服务提供商专门用来处理认证的服务器，简单点说就是登录功能（验证用户的账号密码是否正确以及分配相应的权限）
6. 资源服务器（Resource server）： 即服务提供商存放用户生成的资源的服务器。它与认证服务器，可以是同一台服务器，也可以是不同的服务器。简单点说就是资源的访问入口，比如上节中提到的“云笔记服务”和“云相册服务”都可以称之为资源服务器。




#### Spring Oauth2代码开发

**Spring oauth2调用逻辑**
如下图
![Spring oauth2调用逻辑](../../../../etc/distributed/cloud/security/oauth2_process.png)

Spring boot 和spring security 版本 版本：

```xml
     <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.4.RELEASE</version>
    </parent>
 
     <dependencies>
        <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>

     </dependencies>

```
spring security oauth2中对于认证和授权是都有多种方式，常用的有内存（写demo用用而已）和数据库的（实际项目用的比较多），




**推荐**
在开发的时候打开Spring-security的debug日志，方便问题定位
```yml
logging:
  level:
    org:
      springframework.security: DEBUG
```
##### 基于内存

1. 对于spring security 而言，我们需要设置几个用户进去，也就是设置我们的认证功能

详细代码如下

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author John·Louis
 * @date create in 2019/11/9
 * description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
      @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//                 在内存中创建用户并为密码加密
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("user")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");

    }
}

```
2. 对于认证服务器而言，我们需要添加clientId和ClientSecret，来做一个授权，表示这个资源是谁的，
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
/**
 * @author John·Louis
 * @date create in 2019/11/9
 * description:
 * 配置认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

     @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置客户端
        clients
                // 使用内存设置
                .inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret(passwordEncoder.encode("secret"))
                // 授权类型
                .authorizedGrantTypes("password","authorization_code")
                // 授权范围
                .scopes("app")
                // 注册回调地址
                .redirectUris("http://www.baidu.com");
    }
}
```


**注意：**
在以上的代码中我们都BCryptPasswordEncoder包装了我们的密码，这主要是因为在Spring Security: 5.1.4.RELEASE之后的版本，
默认是需要密码加密的，不能使用明文密码，如果我们传输的是明文密码，那么我们就会收到一个报错信息
`java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"` 



如下代码到浏览器中获取获取code
```
http://localhost:9991/oauth/authorize?client_id=client&response_type=code
```

最后跳转到登录地址，登录完成重定向到redirectUri，拿到code

如下图
![Spring oauth2调用逻辑](../../../../etc/distributed/cloud/security/oauth2_login.png)

![Spring oauth2调用逻辑](../../../../etc/distributed/cloud/security/oauth2_getCode.png)


>需要记住我们获取token的时候需要吧clientId和clientSecret设置进去

![Spring oauth2调用逻辑](../../../../etc/distributed/cloud/security/oauth2_getToken_clientId.png)
```
curl -X POST -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=1JuO6V' "http://client:secret@localhost:9991/oauth/token"
```

获取token
![Spring oauth2调用逻辑](../../../../etc/distributed/cloud/security/oauth2_getToken_body_authorization_code.png)
最后响应
```json
{
    "access_token": "267468c6-1cb6-449c-8231-3dd2c8bcedfc",
    "token_type": "bearer",
    "expires_in": 26511,
    "scope": "app"
}
```

如果我们觉得这样获取token特别复杂，那么我们其实也可以通过`grant_type=password`这样一种方式，不过如果设置成`grant_type=password`我们需要在`WebSecurityConfig`中注册Bean
来讲注册的bean使用到`AuthorizationServerConfiguration`中，否则authorizedGrantType不支持password

在`WebSecurityConfig`中注册bean
```java 
 @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//
    @Override
    @Bean("userDetailsService")
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

```
在`AuthorizationServerConfiguration`中注入bean

```java

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    //将authenticationManager设置到环境中，否则authorizedGrantType不支持password
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .setClientDetailsService(jdbcDetailsService());

    }

```

```
curl -X POST -H "Content-Type: application/x-www-form-urlencoded"  -d 'grant_type=password&scope=app&username=admin&password=admin' "http://client:secret@localhost:9991/oauth/token"

```
![Spring oauth2调用逻辑](../../../../etc/distributed/cloud/security/oauth2_gettoken_body_password.png)

**通过这个token在其他服务调用资源**
    
接下来我们就可以拿到我们的token去访问资源服务器的资源的，说白一点就是我们本来想要访问的url

**但是资源服务器凭什么认这个资源，所以其实我们首先需要在配置文件中配置好的，就是资源服务器去哪个地方校验这个token是正确的？**





这里有一个问题，**如果我们资源服务器有些资源是只有部分权限才能访问的**

=========================todo=====================  
**调取认证服务器的其他地址：**
调取认证服务的相关地址有两种方式
- 将认证服务器同事设置为资源服务器，那么带着已经认证好了的token就可以访问相关资源了
- 放开相关地址(endpoint)的认证权限






**通过这个token在其他服务调用资源**

**注意：**
当我们通过token访问资源服务器的时候，发现token失效了我们不应该把上面的流程重新走一遍，我们应该是将当前失效的token拿着重新刷新token



##### 基于jdbc形式的

1. 基于RBAC模型

#### Spring-Oauth高阶开发

##### WebSecurityConfigurerAdapter中相关功能以及设置方式

1. AuthenticationManagerBuilder
2. WebSecurity
3. HttpSecurity

##### AuthorizationServerConfigurerAdapter中相关功能以及设置方式

1. ClientDetailsServiceConfigurer
2. AuthorizationServerEndpointsConfigurer
3. AuthorizationServerSecurityConfigurer

##### ResourceServerConfigurerAdapter中相关功能以及设置方式

1. HttpSecurity
2. ResourceServerSecurityConfigurer

##### spring security oauth2 个性化实战开发


1. 自定义登录URL与跳转地址

2. security 

3. 自定义过滤器




#### 在Spring cloud中Spring-Oauth2与Zuul 结合实现网关认证功能

#### Spring-Oauth2在Spring-中通过社交平台授权登录


