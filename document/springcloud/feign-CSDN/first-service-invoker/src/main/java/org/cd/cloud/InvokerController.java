package org.cd.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvokerController {

    @Autowired
    private MyFeignClient myFeignClient;

    @RequestMapping("/test")
    public String getMsg() {
        return myFeignClient.message(999L);
    }

    @RequestMapping("/testMyFeign")
    public String testMyFeign() {
        return myFeignClient.testMyFeign();
    }

}
