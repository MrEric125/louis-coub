package com.louis.objectcontainer;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.louis.bootmybatis.common.WrapMapper;
import com.louis.bootmybatis.common.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import swiftsdk.SfOssClient;
import swiftsdk.errors.SfOssException;
import swiftsdk.util.TokenCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author louis
 * <p>
 * Date: 2019/8/12
 * Description:
 */
@Slf4j
@Controller
public class SwiftController {

    private HttpServletRequest request;

    private HttpServletResponse response;

    private static final String UNZIPPATH = "/nfsc/ELOG_FLUX_WMO/issue/unzip/";
    private static final String UNZIPDEST = "/nfsc/ELOG_FLUX_WMO/issue/target/";

    public static final String YYYYMMDD = "yyyy-MM-dd";

    private static final String PATH_SEPERATE="/";



    @Autowired
    SfOssClient sfOssClient;

    @Autowired
    TokenCache tokenCache;

    @Autowired
    SwiftExtender swiftExtender;


    @RequestMapping("/deleteContainer")
    @ResponseBody
    public Wrapper deleteContainers(@RequestParam String container) {
        boolean b = swiftExtender.deleteContainersWithFile(container);
        return WrapMapper.ok(b);
    }



    @RequestMapping("/upload")
    @ResponseBody
    public Wrapper uploadFile(@RequestParam("objName")String objName,@RequestParam("path")String path) {
        Boolean b ;
        try {
            b= sfOssClient.uploadFile("nfsc", objName, path );


        } catch (SfOssException e) {
            e.printStackTrace();
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(b);
    }




    @RequestMapping("/uploadTar")
    @ResponseBody
    public Wrapper uploadTar( @RequestParam(required = false) String container,@RequestParam String path) {
        boolean nfsc;
        String unzipPath = "";

        String targetBasePath =UNZIPDEST;

        String unZipDest = unzipPath + FileUtil.uuID() + File.separator;

        String dataString= new SimpleDateFormat(YYYYMMDD).format(new Date());
//        首先将文件上传到unZipDest

        String warehouseCode = "523DCE";

        String username = "123456";
        String diviceId = "diviceId";
        String destDirPath = targetBasePath + StringUtils
                .join(Lists.newArrayList(dataString, warehouseCode, username, diviceId), PATH_SEPERATE) + PATH_SEPERATE;

        try {

            nfsc = sfOssClient.uploadTar(container == null ? "tmp" : container,path);
            List<String> objectList = sfOssClient.getObjectList("tmp", null, 1000);
            List<String> containerList = sfOssClient.getContainerList(null, null);
            log.info("objName:{}", objectList);
            objectList.forEach(x->{
                try {
                    if (CollectionUtils.isEmpty(containerList) || !containerList.contains("nfsc")) {
                        sfOssClient.createContainer("nfsc");
                    }
                    swiftExtender.copyObj("tmp", "nfsc",
                            x, destDirPath + FileUtil.convertFileName(x));

                } catch (SfOssException e) {
                    e.printStackTrace();
                }
            });
            //删除缓存文件夹
            sfOssClient.deleteContainer("tmp", true);

        } catch (SfOssException e) {
            e.printStackTrace();
            return WrapMapper.error(e.getMessage());
        }
        return WrapMapper.ok(nfsc);
    }

    @RequestMapping("/uploadTarByFile")
    @ResponseBody
    public Wrapper uploadTarByFile(@RequestParam("file") MultipartFile multipartFile) throws IOException, SfOssException {

        String filename = multipartFile.getOriginalFilename();
        String prefix = filename.substring(filename.lastIndexOf("."));
        File file=File.createTempFile(UUID.randomUUID().toString(), prefix);
        multipartFile.transferTo(file);
        sfOssClient.createContainer("tmp");
        boolean tmp = sfOssClient.uploadTar("tmp", file.getPath());

//        boolean tmp = swiftExtender.uploadTar("tmp", file, multipartFile.getOriginalFilename());
        return WrapMapper.ok(tmp);
    }

    @RequestMapping("/getContainer")
    @ResponseBody
    public Wrapper getContainer(
                          @RequestParam(required = false) String beganName,
                          @RequestParam(required = false) Integer limit,
                          @RequestParam(required = false) String prefix,
                          @RequestParam(required = false) String endMarker
                         ) throws SfOssException {
        List<String> objectList = sfOssClient.getContainerList(
                beganName, limit == null ? 1000 : limit, prefix, endMarker);
        return WrapMapper.ok(objectList);

    }

    @RequestMapping("/createContainer")
    @ResponseBody
    public Wrapper createContainer(@RequestParam String container) throws SfOssException {
        boolean container1 = sfOssClient.createContainer(container);
        return WrapMapper.ok(container1);
    }

    /**
     * 模拟项目中的使用上传zip文件
     *
     * @return
     */
    @RequestMapping("/uploadZip")
    @ResponseBody
    public Wrapper uploadZip(HttpServletRequest request,HttpServletResponse response) throws IOException, SfOssException, FileUploadException {

//        InputStream inputStream = file.getInputStream();
//        sfOssClient.uploadObject("tmp", file.getOriginalFilename(), inputStream);
//        Map<String ,Object> map = new HashMap<>();
//        map.put("original", file.getOriginalFilename());
//        map.put("name", file.getName());

        String tmp = swiftExtender.getObjectPath("tmp", null);
        log.info("objPath====<<<<<{}", tmp);


//        sfOssClient.uploadTar("tmp", map1);
//        sfOssClient.uploadTar("tmp", tmp);
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> fileItems = upload.parseRequest((RequestContext) request);
        for (FileItem file : fileItems) {
            if (file.isFormField()) {
                String fieldName = file.getFieldName();
                String name = file.getName();
                log.info("fieldName:===>>>{}", fieldName);
                log.info("fileName:===>>>{}", name);
                InputStream inputStream = file.getInputStream();


            }


        }


        return WrapMapper.ok(tmp);

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

    @RequestMapping("/copyFile")
    @ResponseBody

    public Wrapper copyFile(@RequestParam String fromObjName, @RequestParam String destObjName) {
        boolean b = swiftExtender.copyObj("nfsc", "nfsc", fromObjName, destObjName);
        return WrapMapper.ok(b);
    }

    @RequestMapping("/getObj")
    @ResponseBody
    public Wrapper getObj(@RequestParam(required = false) String container,
                          @RequestParam(required = false) String beganName,
                          @RequestParam(required = false) Integer limit,
                          @RequestParam(required = false) String prefix,
                          @RequestParam(required = false) String endMarker,
                          @RequestParam(required = false) String delimiter) throws SfOssException {
        List<String> objectList = sfOssClient.getObjectList(container == null ? "tmp" : container,
                beganName, limit == null ? 1000 : limit, prefix, endMarker, delimiter);
        return WrapMapper.ok(objectList);

    }





}
