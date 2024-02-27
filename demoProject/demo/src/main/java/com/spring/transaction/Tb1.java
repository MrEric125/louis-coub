package com.spring.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

import java.util.Date;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "tb1")
//@DynamicUpdate(true)
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Tb1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @Column(name = "OP_TIME")
    private Date opTime;


}
