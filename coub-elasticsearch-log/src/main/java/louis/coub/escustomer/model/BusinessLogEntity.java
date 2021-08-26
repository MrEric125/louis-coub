package louis.coub.escustomer.model;

import lombok.Data;
import louis.coub.escustomer.registry.EsModel;

import java.util.Date;

@Data
@EsModel(index = "bussinesslog",type = "log",template = "businessLogTemplate.json")
public class BusinessLogEntity {

    private String id;

    private Date timestamp;

    private String content;

    private String logLevel;



}
