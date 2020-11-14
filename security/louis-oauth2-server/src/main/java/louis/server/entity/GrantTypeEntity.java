package louis.server.entity;

import lombok.*;
import louis.server.entity.authbase.BaseEntity;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "grant_type")
public class GrantTypeEntity extends BaseEntity<Long> {

    @Column(name = "value", nullable = false)
    private String value;

}
