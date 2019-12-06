package com.oauth2.provider.security;

import com.oauth2.provider.security.provider.PcAuthorizeConfigManager;
import com.oauth2.provider.service.impl.UserService;
import com.oauth2.provider.security.token.TokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PcAuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Primary
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**/*", "/**/*.css", "/**/*.js");

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserService();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * // TODO: 2019/11/19 目前的策略是只要有一个角色不对，就拒绝访问
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()                                // 定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")                        // 设置登录页面
//                .loginProcessingUrl("/login") // 自定义的登录接口
                .defaultSuccessUrl("/").permitAll()        // 登录成功之后，默认跳转的页面
                .and()
                .authorizeRequests()                    // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/user/register", "/login").permitAll()        // 设置所有人都可以访问登录页面
//                .anyRequest().authenticated()                // 任何请求,登录后可以访问
                .and()
                .csrf().disable();                    // 关闭csrf防护
//                .logout()
//                .logoutSuccessUrl("/login")
//                .httpBasic();
//        http.headers().cacheControl();
        super.configure(http);


//        我们可以看到不论我们配置的是antMatches("/**").hasAnyRole("/admin"),
//        其实就是配置了一个ExpressionInterceptUrlRegistry，所依我们用一下的方式自动配置一个查询url的方式
        authorizeConfigManager.config(http.authorizeRequests());

//        http.authorizeRequests().anyRequest().authenticated();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
        auth.authenticationProvider(tokenAuthenticationProvider);

    }
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(0);
        return new CorsFilter(source);
    }
}
