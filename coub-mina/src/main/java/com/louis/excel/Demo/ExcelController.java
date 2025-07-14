package com.louis.excel.Demo;

import com.alibaba.excel.EasyExcelFactory;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * @author jun.liu
 * @since 2021/2/5 15:50
 */
@RestController

public class ExcelController {

    public void down() {
        ByteArrayOutputStream bas = new ByteArrayOutputStream();
        EasyExcelFactory.write(bas, ProductExcelModel.class)
                .registerWriteHandler(new SpinnerWriteHandler()).sheet().doWrite(new ArrayList());

    }
}
