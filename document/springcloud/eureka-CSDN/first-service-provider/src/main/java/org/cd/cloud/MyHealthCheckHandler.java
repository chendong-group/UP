package org.cd.cloud;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @classname: MyHealthCheckHandler
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-09 16:31
 */
@Component
public class MyHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    private MyHealthIndicator myHealthIndicator;

    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus currentStatus) {
        Status status = myHealthIndicator.health().getStatus();
        if (Status.UP.equals(status)) {
            System.out.println("数据库连接正常");
            return InstanceInfo.InstanceStatus.UP;
        }else {
            System.out.println("数据库连接异常");
            return InstanceInfo.InstanceStatus.DOWN;
        }
    }
}
