package louis.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author louis
 * <p>
 * Date: 2019/11/29
 * Description:
 */
@Configuration
@EnableJpaAuditing
public class AuditingConfig {

    @Bean
    public AuthenticationTrustResolver authenticationTrustResolver() {
        return new AuthenticationTrustResolverImpl();
    }

    @Bean
    public AuditorAware<String> auditorAwareBean(@Autowired AuthenticationTrustResolver authenticationTrustResolver) {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authenticationTrustResolver.isAnonymous(authentication)) {
                return Optional.of("@SYSTEM");
            }

            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                return Optional.of((String) principal);
            } else if (principal instanceof UserDetails) {
                return Optional.of(((UserDetails) principal).getUsername());
            } else {
                return Optional.of(String.valueOf(principal));
            }
        };
    }
}
