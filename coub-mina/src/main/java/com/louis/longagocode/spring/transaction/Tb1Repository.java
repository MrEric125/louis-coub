package com.louis.longagocode.spring.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author louis
 * <p>
 * Date: 2020/1/3
 * Description:
 */
@Repository
public interface Tb1Repository extends JpaRepository<Tb1, Long> {

}
