package louis.coub.es;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document(indexName = "book")
public class Book {

    @Field
    private String id;

    @Field
    private String bookName;

    @Field
    private String ISBN;
}
