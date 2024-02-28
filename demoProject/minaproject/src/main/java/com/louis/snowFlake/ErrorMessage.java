package com.louis.snowFlake;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity(name = "error_message")
public class ErrorMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableName;

    private Long tableId;






}
