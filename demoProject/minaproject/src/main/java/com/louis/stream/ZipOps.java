package com.louis.stream;


import com.alibaba.excel.util.DateUtils;
import com.louis.common.common.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
@Slf4j
@RestController
@RequestMapping("/zipOps")
public class ZipOps {


    @RequestMapping("/zipOps")
    public HttpResult zipOps() {
        entranceStream();
        return HttpResult.ok();
    }

    public void entranceStream() {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(os)) {

            // 流的回调 执行逻辑
            Consumer<Workbook> consumer = workbook -> {
                String itemFileName = DateUtils.format(new Date());
                try {
                    ZipEntry zipEntry = new ZipEntry(itemFileName + ".xls");

                    zipOutputStream.putNextEntry(zipEntry);

                    //这里讲一下，workBook.write会指定关闭数据流，如果这里直接用workbook.write(out)，下次就会抛出out已被关闭的异常，所有用ByteArrayOutputStream来拷贝一下。
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    workbook.write(bos);
                    bos.writeTo(zipOutputStream);
                    bos.flush();
                    zipOutputStream.flush();
                    os.flush();

                } catch (Exception e) {
                    log.error("数据下载时，传输异常:{}", itemFileName, e);
                }
            };
            doResource(consumer);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void doResource(Consumer<Workbook> consumer) {
        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet");

        consumer.accept(workbook);
    }


}
