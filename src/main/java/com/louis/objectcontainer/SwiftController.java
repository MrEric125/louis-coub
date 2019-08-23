package com.louis.objectcontainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.louis.bootmybatis.common.WrapMapper;
import com.louis.bootmybatis.common.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swiftsdk.SfOssClient;
import swiftsdk.errors.SfOssException;
import swiftsdk.util.TokenCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author louis
 * <p>
 * Date: 2019/8/12
 * Description:
 */
@Controller
public class SwiftController {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private static final String UNZIPPATH = "/nfsc/ELOG_FLUX_WMO/issue/unzip/";
    private static final String UNZIPDEST = "/nfsc/ELOG_FLUX_WMO/issue/target/";





    @Autowired
    SfOssClient sfOssClient;

    @Autowired
    TokenCache tokenCache;

    @Autowired
    SwiftExtender swiftExtender;

    @RequestMapping("/obj/{path}")
    public String getObject(@PathVariable("path") String path, @RequestParam("obj") String obj, HttpServletResponse response) throws IOException {

        InputStream object = null;
        try {
            object = sfOssClient.getObject(path, obj);

        } catch (SfOssException e) {
            e.printStackTrace();
        }
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("zhangsan", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(object.available());


        return null;
    }



    @RequestMapping("/con/containers")
    @ResponseBody
    public Wrapper getContainers() throws SfOssException {
        List<String> containerList = sfOssClient.getContainerList(null, 100);
        return WrapMapper.ok(containerList);
    }

    @RequestMapping("/upload")
    @ResponseBody

    public Wrapper uploadFile(@RequestParam("objName")String objName,@RequestParam("filePath")String filePath) {
        Boolean b ;
        try {
            b= sfOssClient.uploadFile("nfsc", objName, filePath );


        } catch (SfOssException e) {
            e.printStackTrace();
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(b);
    }
    @RequestMapping("/uploadTar")
    @ResponseBody

    public Wrapper uploadTar(@RequestParam("filePath")String filePath) {
        boolean nfsc = false;
        try {
             nfsc = sfOssClient.uploadTar("nfsc", filePath);
        } catch (SfOssException e) {
            e.printStackTrace();
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(nfsc);
    }

    /**
     * 模拟项目中的使用上传zip文件
     *
     * @return
     */
    @RequestMapping("/uploadZip")
    @ResponseBody

    public Wrapper uploadZip(@RequestParam MultipartFile file) throws FileNotFoundException {

        String upZipDest=UNZIPDEST + StringUtils.replace(UUID.randomUUID().toString(), "-", "") + File.separator;

        File zipFile = new File("");
        InputStream inputStream = new FileInputStream(zipFile);

        try {
            sfOssClient.uploadObject("nfsc",file.getOriginalFilename(), file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SfOssException e) {
            e.printStackTrace();
        }
        return WrapMapper.ok(file.getOriginalFilename());
    }


    @RequestMapping("/deleteFile")
    @ResponseBody

    public Wrapper delete(@RequestParam(name = "objName",required = false)String objName) {
        boolean b = false;
        Map<String, String> map = Maps.newHashMap();
        InputStream inputStream = null;
        try {
//            map = sfOssClient.headObjectMeta("nfsc", objName);
            inputStream = sfOssClient.getObject("nfsc", objName);
//            b= sfOssClient.deleteObject("nfsc", objName);
        } catch (SfOssException e) {
            if (!StringUtils.equals("Resource does not exist", e.getMessage())) {
                e.printStackTrace();
            }
        }
        map.put("isExist", String.valueOf(inputStream != null));
//        map.put("delete", String.valueOf(b));
        return WrapMapper.ok(map);


    }

    @RequestMapping("/token")
    @ResponseBody

    public Wrapper getToken() {
        String token = tokenCache.getToken();
        return WrapMapper.ok(token);
    }

    @RequestMapping("/writeuuid")
    @ResponseBody

    public  Wrapper writeFileWithUUIDFileName(@RequestParam MultipartFile file) {
        String destObjName =  FileUtil.convertFileName(file.getOriginalFilename());
        try {
//            InputStream inputStream = new FileInputStream(file);
            sfOssClient.uploadObject("nfsc", destObjName, file.getInputStream());
            return WrapMapper.ok(destObjName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SfOssException e) {
            e.printStackTrace();
        }
        return WrapMapper.error();
    }


    @RequestMapping("/mataData")
    @ResponseBody

    public Wrapper mateData(@RequestParam(required = false) String container, @RequestParam String objName) {
        Map<String, String> map = Maps.newHashMap();
        try {
            map = sfOssClient.headObjectMeta(container == null ? "nfsc" : container, objName);
        } catch (SfOssException e) {
            e.printStackTrace();
        }
        return WrapMapper.ok(map);
    }


    @RequestMapping("/getObjList")
    @ResponseBody

    public Wrapper deleteByTime() {
        List<String> objectList = Lists.newArrayList();

        try {
            objectList=sfOssClient.getObjectList("nfsc", null, null, "ELOG_FLUX_WMO", null, null);
//            objectList= sfOssClient.getObjectList("nfsc", "ELOG_FLUX_WMO", 100);

        } catch (SfOssException e) {
            e.printStackTrace();
        }
        return WrapMapper.ok(objectList);
    }

    @RequestMapping("/copyFile")
    @ResponseBody

    public Wrapper copyFile(@RequestParam String fromObjName, @RequestParam String destObjName) {
        boolean b = swiftExtender.copyObj("nfsc", "nfsc", fromObjName, destObjName);
        return WrapMapper.ok(b);
    }




}
