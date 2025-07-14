package com.louis.excel.Demo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

public class ProductExcelModel {

    @ExcelIgnore
    private String id;

    @ExcelProperty(value = "*商品名称", index = 0)
    private String goodsName;

    @ExcelProperty(value = "商品品牌", index = 1)
    private String brand;

    @ExcelProperty(value = "*货号", index = 2)
    private String productCode;

    @ExcelProperty(value = "*是否管制品", index = 3)
    private String dangerousMark;

    @ExcelProperty(value = "*商品类型(只允许填写：1、普通商品;2、易制爆;3、易制毒;4、剧毒品,5、精神麻醉品，6、医用毒性品、7、民用爆炸品;)", index = 4)
    private String goodType;

    @ExcelProperty(value = "*规格", index = 5)
    private String specification;

    @ExcelProperty(value = "*单位(个、盒、箱 等)", index = 6)
    private String unit;

    @ExcelProperty(value = "*发布数量", index = 7)
    private String quantity;

    @ExcelProperty(value = "CAS(危化品必填)", index = 8)
    private String casNo;

    @ExcelProperty(value = "*存放校区(只允许填写：1、南校园；2、北校园；3、东校园；4、珠海区)", index = 9)
    private String campus;

    @ExcelProperty(value = "商品描述（非必填项，填写商品的详细信息，包含但不限于生产厂家，开封情况，目前存放具体点。200字以内）", index = 10)
    private String remark;
}
 
