package com.louis.cofig;

import com.louis.provider.PcAuthorizeConfigManager;
import com.louis.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author louis
 * <p>
 * Date: 2019/11/18
 * Description:
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PcAuthorizeConfigManager authorizeConfigManager;

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
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/index.html","login").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error.html").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login.html")
                .and()
                .authorizeRequests().anyRequest().authenticated();


//        我们可以看到不论我们配置的是antMatches("/**").hasAnyRole("/admin"),
//        其实就是配置了一个ExpressionInterceptUrlRegistry，所依我们用一下的方式自动配置一个查询url的方式
        authorizeConfigManager.config(http.authorizeRequests());

        http.authorizeRequests().anyRequest().authenticated();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());

    }
}
