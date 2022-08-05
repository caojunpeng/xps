package com.cao.xps.app.files;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cao.xps.common.utils.XpsFileUtils;
import com.cao.xps.common.utils.XpsStringUtils;
import com.cao.xps.service.files.entity.Files;
import com.cao.xps.service.files.service.IFilesService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("files")
public class FilesController {

    private static Logger logger= LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private IFilesService filesService;

    //安装路径
    @Value("#{ @environment['app.collect.untarpath'] ?: T(java.lang.System).getProperty('user.dir')}")
    private String installDir;


    @RequestMapping("/folderMain")
    public ModelAndView menuList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/folder/folderMain");
        return modelAndView;
    }

    /**
     * 获取文件路径
     * @param userName
     * @param fileType
     * @return
     */
    @ResponseBody
    @RequestMapping("/getFilePathList")
    public List<String> getFilePathList(String userName,Integer fileType){
        List<String> result=new ArrayList<>();
        QueryWrapper<Files> filesQueryWrapper=new QueryWrapper<Files>().eq("file_type",fileType).eq("user_name",userName).last("limit 10");
        List<Files> list = filesService.list(filesQueryWrapper);
        if(!list.isEmpty()){
            for (Files files : list) {
                String realPath = XpsFileUtils.formatPath(installDir+files.getLocalpath());
                String path = new String(Base64.encodeBase64(XpsFileUtils.formatPath(realPath).getBytes()));
                result.add(path);
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/insertFiles", method = RequestMethod.POST)
    public boolean insertFiles(@RequestParam("files") MultipartFile[] multipartFiles, String userName){
        boolean result=false;
        try {
            if(multipartFiles.length==0 || !XpsStringUtils.isNotBlank(userName)){
                return result;
            }
            for(MultipartFile multipartFile:multipartFiles){
                String fileName=multipartFile.getOriginalFilename();
                String end=fileName.substring(fileName.indexOf(".")+1);
                String path="";
                Integer fileType;
                if(XpsFileUtils.pictoreList.contains(end)){
                    path="resourceFiles" + File.separator+userName+ File.separator+"pictore";
                    fileType=1;
                }else if(XpsFileUtils.voiceList.contains(end)){
                    path="resourceFiles" + File.separator+userName+ File.separator+"voice";
                    fileType=2;
                }else if(XpsFileUtils.videoList.contains(end)){
                    path="resourceFiles" + File.separator+userName+ File.separator+"video";
                    fileType=3;
                }else{
                    path="resourceFiles" + File.separator+userName+ File.separator+"file";
                    fileType=4;
                }
                File file=new File(installDir+ File.separator+path);
                //创建文件夹
                XpsFileUtils.forceMkdir(file);
                //文件上传
                File file1 = XpsFileUtils.multipartFileToFile(multipartFile, installDir+ File.separator+path+File.separator+fileName);
                String localPath=".\\"+path+File.separator+fileName;
                //保存入库
                QueryWrapper<Files> filesQueryWrapper=new QueryWrapper<Files>().eq("localpath",localPath).eq("user_name",userName);
                Files files=filesService.getOne(filesQueryWrapper);
                if(files==null){
                    files=new Files();
                }
                files.setUserName(userName);
                files.setCreateTime(LocalDateTime.now());
                files.setFileName(fileName);
                files.setFileSize(Double.valueOf(multipartFile.getSize()));
                String md5 = XpsFileUtils.getMd5ByFile(file1, "MD5");
                files.setFile(md5);
                files.setLocalpath(localPath);
                files.setFileType(fileType);
                filesService.saveOrUpdate(files);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 图片解析（base64）
     * @param p base64加密后的文件路径
     * @param response
     * @throws Exception
     */
    @RequestMapping("/img")
    public void getImage(String p, HttpServletResponse response) throws Exception {
        if (XpsStringUtils.isNotBlank(p)) {
            p=p.replaceAll(" ", "+");
        }
        String path = new String(Base64.decodeBase64(p.getBytes()));
        File file = new File(path);
        if (file.exists()) {
            File jpg = new File(file.getPath()+".jpg");
            if (path.endsWith("HEIC")){
                if (jpg.exists()) {
                    file = jpg;
                }
            }
            if (XpsStringUtils.isNotBlank(file.getName()) &&( !file.getName().contains(".")||file.getName().endsWith(".pic")||file.getName().endsWith(".pic_thum"))) {
                response.setHeader("content-disposition", "attachment;filename=" + file.getName() + ".jpg");
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + file.getName());
            }

            response.setHeader("content-type", "image/jpeg");
            InputStream input = FileUtils.openInputStream(file);
            OutputStream output = response.getOutputStream();
            IOUtils.copy(input, output);
            output.flush();
            IOUtils.closeQuietly(input);
        }
    }

    /**
     * 音频解析
     * @param p
     * @param notWeChat
     * @param response
     * @throws Exception
     */
    @RequestMapping("/aud")
    public void getAud(String p,String notWeChat, HttpServletResponse response) throws Exception {
        String path1 = new String(Base64.decodeBase64(p.getBytes()));
        File file1 = new File(path1);
        if (!file1.exists()) {
            logger.info("file1文件不存在....");
        }else {
            logger.info("file1文件存在....");
        }
        if (XpsStringUtils.isNotBlank(p)){
            p=p.replaceAll(" ", "+");
        }
        String path = new String(Base64.decodeBase64(p.getBytes()));
        File file = new File(path);
        if (!file.exists()) {
            logger.info("aud文件不存在....");
            return;
        }
        File wav= new File("");
        if (XpsStringUtils.isNotBlank(notWeChat)){
            wav = new File(file.getPath());
            //amr解码
            if (file.getPath().endsWith(".amr")) {
                File cdk = new File(file.getPath() + ".wav");
                if (!cdk.exists()) {
                    XpsStringUtils.decodeAmr(null, file.getPath(), file.getPath() + ".wav");
                    Thread.sleep(1000);
                    if (cdk.exists()) {
                        wav = cdk;
                    }
                }else {
                    wav = cdk;
                }
            }
        }
        response.setHeader("content-disposition", "attachment;filename=" + wav.getName());
        response.setHeader("Content-Length", wav.length() + "");
        InputStream input = FileUtils.openInputStream(wav);
        OutputStream output = response.getOutputStream();
        IOUtils.copy(input, output);
        output.flush();
        IOUtils.closeQuietly(input);
    }

}
