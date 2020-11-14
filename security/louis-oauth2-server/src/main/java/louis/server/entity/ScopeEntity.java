package louis.server.entity;

import lombok.*;
import louis.server.entity.authbase.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "scope")
public class ScopeEntity extends BaseEntity<Long> {

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

}
