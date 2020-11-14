package louis.server.entity.asocciate;

import lombok.*;
import louis.server.entity.ClientDetailsEntity;
import louis.server.entity.ScopeEntity;
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
@Table(name = "client_details_scope_multi")
public class ClientDetailsToScopes extends BaseEntity<Long> {


    private static final long serialVersionUID = -4115343148840083465L;
    @NonNull
    @NotNull
    @Column(name = "auto_approve", nullable = false)
    private Boolean autoApprove;

    @NonNull
    @NotNull
    @Column(name = "client_details_id")
    private ClientDetailsEntity clientDetails;

    @NonNull
    @NotNull
    @Column(name = "scope_id")
    private ScopeEntity scope;
}
