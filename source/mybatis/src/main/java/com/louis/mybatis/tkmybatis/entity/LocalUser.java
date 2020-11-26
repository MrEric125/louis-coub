package com.louis.mybatis.tkmybatis.entity;

import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Table(name = "local_user")
public class LocalUser implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "age")
    private Integer age;

    @Column(name = "fivarite")
    private String fivarite;
}