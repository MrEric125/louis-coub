package louis.server.entity.asocciate;

import lombok.*;
import louis.server.entity.authbase.BaseEntity;

import javax.persistence.*;

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
@Table(name = "role_authority_multi")
public class RoleAuthority extends BaseEntity<Long> {
    @Column(name = "role_id", nullable = false)
    private Long role;

    @Column(name = "authority_id", nullable = false)
    private Long authority;

}
