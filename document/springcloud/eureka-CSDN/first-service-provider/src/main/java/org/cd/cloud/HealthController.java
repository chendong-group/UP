package org.cd.cloud;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: HealthController
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-09 15:45
 */
@RestController
public class HealthController {

    static Boolean dbConnectionIsOk = false;

    @RequestMapping("/dbstate/{state}")
    public String setConnectState(@PathVariable("state") Boolean state) {
        dbConnectionIsOk = state;
        return "当前数据库连接是否正常：" + dbConnectionIsOk;
    }
}
