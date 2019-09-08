package com.louis.bootmybatis.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author louis
 * <p>
 * Date: 2019/7/9
 * Description:
 */
@Data
@Document(indexName = "website",type = "blog")
public class BlogInfo implements Serializable {

    private String id;

    @Field(analyzer = "first_name",pattern = "first_name")
    private String firstName;

    @Field(analyzer = "last_name",pattern = "last_name")
    private String lastName;

    private int age;

    private String about;








}
