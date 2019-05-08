package org.cd.cloud;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

/**
 * @classname: MyHealthIndicator
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-09 15:44
 */
@Component
public class MyHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if (HealthController.dbConnectionIsOk) {
            return new Health.Builder(Status.UP).build();
        }else {
            return new Health.Builder(Status.DOWN).build();
        }
    }
}
