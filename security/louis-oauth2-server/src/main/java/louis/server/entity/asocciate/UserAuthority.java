package louis.server.entity.asocciate;

import lombok.*;
import louis.server.entity.AuthorityEntity;
import louis.server.entity.UserEntity;
import louis.server.entity.authbase.BaseEntity;
import org.hibernate.annotations.Where;

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
@Table(name = "user_authority_multi")
public class UserAuthority extends BaseEntity<Long> {
    @Column(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "authority_id", nullable = false)
    @Where(clause = "disabled = False")
    private AuthorityEntity authority;
}
