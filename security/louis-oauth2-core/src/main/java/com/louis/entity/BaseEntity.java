package com.louis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2019/11/27
 * Description:
 */

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Persistable<ID>,Serializable {

    private static final long serialVersionUID = 8513406771296804237L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Override
    public  ID getId(){
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @JsonIgnore
    public String getIdToString(){
        return String.valueOf(id);
    }
    @Override
    public boolean isNew() {
        return null == getId();
    }

}
