package louis.coub.esCustom.model;

import lombok.Data;
import louis.coub.esCustom.registry.EsModel;

import java.util.List;

/**
 * es 实体，与 libraryTemplate.json中的mapping对应的一个Java实体
 * @author jun.liu
 * @since 2021/2/4 10:03
 */
@Data
@EsModel(index = "library",type = "book",template = "libraryTemplate.json")
public class BookEntity implements IBaseEsEntity {

    private String id;

    private String author;

    private List<String> characters;

    private long copies;

    private String otitle;

    private List<String> tags;

    private String title;

    private long year;

    private Boolean available;

    private Review review;

}
