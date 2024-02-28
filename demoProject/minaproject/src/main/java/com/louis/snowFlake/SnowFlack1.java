package com.louis.snowFlake;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity(name = "snow_flack_1")
public class SnowFlack1 {

    @Id
    private Long id;


}
