package louis.server.entity;

import lombok.*;
import louis.server.entity.authbase.BaseEntity;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity<Long> {

    @NotNull
    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    private String name;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean disabled;


}
