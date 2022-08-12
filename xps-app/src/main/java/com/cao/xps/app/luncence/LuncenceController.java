package com.cao.xps.app.luncence;

import com.cao.xps.service.plug.luncence.LuncenceManage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@RequestMapping("/luncence")
@Controller
public class LuncenceController {
    /**
     * 引入application.properties中配置的值
     * "D/123":默认值
     */
    @Value("${app.install.path:D/123}")
    private String install;

    @Resource
    private LuncenceManage luncenceManage;


    @RequestMapping("/createIndex")
    @ResponseBody
    public void createIndex() throws Exception {
        luncenceManage.createIndex();
    }
    @RequestMapping("/searchIndex")
    @ResponseBody
    public void searchIndex() throws Exception {
        luncenceManage.searchIndex();
    }

}
