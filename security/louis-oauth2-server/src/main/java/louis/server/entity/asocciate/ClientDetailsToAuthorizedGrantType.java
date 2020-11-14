package louis.server.entity.asocciate;

import lombok.*;
import louis.server.entity.ClientDetailsEntity;
import louis.server.entity.GrantTypeEntity;
import louis.server.entity.authbase.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author louis
 * <p>
 * Date: 2019/11/28
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client_details_grant_type_multi")
public class ClientDetailsToAuthorizedGrantType extends BaseEntity<Long> {

    private static final long serialVersionUID = -1319851443795306944L;
    @NonNull
    @NotNull
    @Column(name = "client_details_id")
    private ClientDetailsEntity clientDetails;

    @NonNull
    @NotNull
    @Column(name = "grant_type_id")
    private GrantTypeEntity grantType;
}
