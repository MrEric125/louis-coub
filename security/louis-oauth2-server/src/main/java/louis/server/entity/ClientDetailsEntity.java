package louis.server.entity;

import lombok.*;
import louis.server.entity.asocciate.ClientDetailsToAuthorizedGrantType;
import louis.server.entity.asocciate.ClientDetailsToResourceId;
import louis.server.entity.asocciate.ClientDetailsToScopes;
import louis.server.entity.authbase.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString()
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_details")
public class ClientDetailsEntity extends AbstractAuditable<Long> {

    private static final long serialVersionUID = 7100834559771058030L;
    @NonNull
    @NotNull
    @Column(name = "client_id", unique = true, nullable = false, length = 200)
    private String clientId;

    @NonNull
    @NotNull
    @Column(name = "client_secret", nullable = false)
    private String clientSecret;

    @Column(name = "access_token_validity_seconds")
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity_seconds")
    private Integer refreshTokenValiditySeconds;


    @Singular("redirectUri")
    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL )
    private Set<RedirectUriEntity> redirectUris;

    @Singular
    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ClientDetailsToAuthorizedGrantType> authorizedGrantTypes;

    @Singular
    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ClientDetailsToScopes> scopes;

    @Singular
    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ClientDetailsToResourceId> resourceIds;

}
