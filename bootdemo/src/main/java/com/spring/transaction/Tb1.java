package com.spring.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
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
public class Tb1 {

    private Long id;

    private String name;

    private String code;

    @Column(name = "OP_TIME")
    private Date opTime;
}
