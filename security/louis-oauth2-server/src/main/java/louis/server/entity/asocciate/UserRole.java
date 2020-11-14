package louis.server.entity.asocciate;

import lombok.*;
import louis.server.entity.RoleEntity;
import louis.server.entity.UserEntity;
import louis.server.entity.authbase.BaseEntity;
import org.hibernate.annotations.Where;

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
@Table(name = "user_role_multi")
public class UserRole extends BaseEntity<Long> {

    @NonNull
    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @NonNull
    @NotNull
    @Column(name = "role_id", nullable = false)
    @Where(clause = "disabled = False")
    private Long roleId;
}
