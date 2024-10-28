package com.louis.snowFlake;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "snow_flack_1")
public class SnowFlack1 {

    @Id
    private Long id;


}
