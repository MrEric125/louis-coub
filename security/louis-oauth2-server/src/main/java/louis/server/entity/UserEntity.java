package louis.server.entity;

import lombok.*;
import louis.server.entity.authbase.AbstractAuditable;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends AbstractAuditable<Long> {



    public static final String NAME_REGEX = "^[A-Za-z0-9_]*$";
    private static final long serialVersionUID = -3386869268621926284L;

    @NotNull
    @Pattern(regexp = NAME_REGEX)
    @Size(max = 50)
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean disabled;

}
