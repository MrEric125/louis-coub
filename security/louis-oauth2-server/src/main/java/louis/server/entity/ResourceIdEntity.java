package louis.server.entity;

import lombok.*;
import louis.server.entity.authbase.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "resource")
public class ResourceIdEntity extends AbstractAuditable<Long> {

    @NonNull
    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

}
