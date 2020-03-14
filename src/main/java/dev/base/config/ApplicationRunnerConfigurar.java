package dev.base.config;

import dev.base.common.ReadXML;
import dev.base.common.WebComm;
import dev.base.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目启动时初始化数据
 */

@Slf4j
@Component
public class ApplicationRunnerConfigurar implements ApplicationRunner {

    @Autowired
    private ReadXML readXML;

    @Autowired
    private UserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List list = userService.getListBySql("select id from [user] where userName='admin'");

        if(list.size()<1){
            int result=userService.register("admin","E10ADC3949BA59ABBE56E057F20F883E");
            if(result>0){
                log.info("admin账号初始化成功");
            }else{
                log.error("admin账号初始化失败");
            }
        }


        readXML.readXML();
    }
}
