package com.louis.snowFlake;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "snow_flack_1")
public class SnowFlack1 {

    @Id
    private Long id;


}
