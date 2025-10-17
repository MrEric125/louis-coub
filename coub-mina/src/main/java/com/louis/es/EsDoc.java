package com.louis.es;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Alias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author John·Louis
 */
@Document(indexName = "#{@indexProvider.indexName()}", aliases = {@Alias("my_index_alias")})
public class EsDoc {


    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword)
    private String content;

    private String type;

    private String createTime;

    private String updateTime;

    private String status;

    private String createUser;

    private String updateUser;

    private String deleteFlag;


}
