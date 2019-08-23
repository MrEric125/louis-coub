package com.louis.objectcontainer;

import com.louis.bootmybatis.common.WrapMapper;
import com.louis.bootmybatis.common.Wrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Hashtable;
import java.util.Properties;

/**
 * @author louis
 * <p>
 * Date: 2019/8/16
 * Description:
 */
@RestController
@RequestMapping("/nfsc")
public class SwiftController2 {

    private static final String CONTAINER = "nfsc";


    @RequestMapping("/unzip")
    public Wrapper unZip(@RequestParam String filePath) {
        Properties properties = new Properties();

        return WrapMapper.ok();
    }



}
