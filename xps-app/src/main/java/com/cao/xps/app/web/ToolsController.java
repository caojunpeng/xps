package com.cao.xps.app.web;

import ch.qos.logback.classic.joran.action.LoggerAction;
import com.cao.xps.common.utils.FileUtils;
import com.cao.xps.common.utils.QRCodeUtils;
import com.cao.xps.common.utils.ResponseUtil;
import com.cao.xps.common.utils.XpsFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
@RequestMapping("/tools")
public class ToolsController {

    //安装路径
    @Value("#{ @environment['app.collect.untarpath'] ?: T(java.lang.System).getProperty('user.dir')}")
    private String installDir;

    private static Logger logger= LoggerFactory.getLogger(ToolsController.class);

    @RequestMapping("/toolsMain")
    public ModelAndView toolsMain() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/tools/tools-Main");
        return modelAndView;
    }


    /**
     * 二维码解析
     */
    @ResponseBody
    @RequestMapping("/analysisQR")
    public String analysisQR(@RequestParam("QRFile")MultipartFile file){
        String result="";
        try {
            if(!file.isEmpty()){
                String fileName=file.getOriginalFilename();
                String path=installDir + File.separator + "QRImages";
                File file1=new File(path);
                if(!file1.exists()){
                    FileUtils.forceMkdir(file1);
                }
                XpsFileUtils.multipartFileToFile(file,path+File.separator+fileName);
                result=QRCodeUtils.decode(path+File.separator+fileName);
                ResponseUtil.printJson("二维码内容",result);
            }
        }catch (Exception e){
            logger.error("二维码解析失败",e);
        }
        return result;
    }
}
