package com.louis.stream;


import com.alibaba.excel.util.DateUtils;
import com.louis.common.common.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Slf4j
@RestController
@RequestMapping("/zipOps")
public class ZipOps {


    @RequestMapping("/zipOps")
    public HttpResult zipOps(Long iter) {
//        entranceStream();
        if (iter == null || iter <= 0) {
            return HttpResult.ok("参数异常");
        }
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(os)) {
                // 流的回调 执行逻辑
                for (int i = 0; i < iter; i++) {

                    Workbook workbook = new SXSSFWorkbook();
                    String itemFileName = DateUtils.format(new Date());

                    try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                        ZipEntry zipEntry = new ZipEntry(UUID.randomUUID().toString() + ".xlsx");
                        zipOutputStream.putNextEntry(zipEntry);
                        workbook.write(bos);
                        bos.writeTo(zipOutputStream);

                    } catch (Exception e) {
                        log.error("数据下载时，传输异常:{}", itemFileName, e);
                    }
                }
                zipOutputStream.flush();
            }
            System.out.println(os.size());
        } catch (Exception e) {
            e.printStackTrace();

        }

//        entranceStream();

        return HttpResult.ok();
    }

    public void entranceStream() {

        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(os)) {

            // 流的回调 执行逻辑
            Consumer<Workbook> consumer = workbook -> {
                String itemFileName = DateUtils.format(new Date());
                try {
                    ZipEntry zipEntry = new ZipEntry(UUID.randomUUID().toString() + ".xlsx");
                    zipOutputStream.putNextEntry(zipEntry);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    workbook.write(bos);
                    bos.writeTo(zipOutputStream);

                } catch (Exception e) {
                    log.error("数据下载时，传输异常:{}", itemFileName, e);
                }
            };
            doResource(consumer);
            System.out.println(os.size());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void doResource(Consumer<Workbook> consumer) {
        for (int i = 0; i < 3; i++) {
            Workbook workbook = new SXSSFWorkbook();
            workbook.createSheet("sheet");
            consumer.accept(workbook);
        }

    }

    public void direact() {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(os)) {

            for (int i = 0; i < 3; i++) {
                String itemFileName = DateUtils.format(new Date());
                Workbook workbook = new SXSSFWorkbook();
                ZipEntry zipEntry = new ZipEntry("数据导出" + i + ".xlsx");
                zipOutputStream.putNextEntry(zipEntry);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                workbook.write(bos);
                bos.writeTo(zipOutputStream);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
