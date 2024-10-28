package com.louis.snowFlake;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity(name = "snow_flack_2")
public class SnowFlack2 {

    @Id
    private Long id;


}
