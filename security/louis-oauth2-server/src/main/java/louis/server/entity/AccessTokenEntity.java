package louis.server.entity;

import lombok.*;
import louis.server.entity.authbase.AbstractAuditable;
import louis.server.util.OAuth2AccessTokenPersistenceConverters;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "access_token")
public class AccessTokenEntity extends AbstractAuditable<Long> {

    private static final long serialVersionUID = -1926207343322991311L;
    @NonNull
    @NotNull
    @Column(name = "token_id", nullable = false, unique = true, length = 36)
    private String tokenId;

    @NonNull
    @NotNull
    @Convert(converter = OAuth2AccessTokenPersistenceConverters.class)
    @Column(name = "serialized_token", nullable = false)
    private OAuth2AccessToken token;

    @NonNull
    @NotNull
    @Column(name = "authentication_id", nullable = false, length = 32)
    private String authenticationId;

    @Size(max = 50)
    @Column(name = "user_name", length = 50)
    private String userName;

    @Size(max = 200)
    @Column(name = "client_id", length = 200)
    private String clientId;

    @NonNull
    @NotNull
    @Lob
    @Column(name = "serialized_authentication", nullable = false)
    private OAuth2Authentication authentication;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "refresh_token_id", nullable = true)
    private RefreshTokenEntity refreshToken;

}
