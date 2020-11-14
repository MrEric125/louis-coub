package louis.server.entity.asocciate;

import lombok.*;
import louis.server.entity.ClientDetailsEntity;
import louis.server.entity.ResourceIdEntity;
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
@Table(name = "client_details_resource_multi")
public class ClientDetailsToResourceId extends BaseEntity<Long> {
    private static final long serialVersionUID = 1919757015565899835L;
    @NonNull
    @NotNull
    @Column(name = "client_details_id")
    private ClientDetailsEntity clientDetails;

    @NonNull
    @NotNull
    @Column(name = "resource_id")
    private ResourceIdEntity resourceId;
}
