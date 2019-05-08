package org.cd.cloud;

import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @classname: TestHealthController
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-09 16:55
 */
@RestController
public class TestHealthController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/testHealth")
    public String testHealth() {
        StringBuilder sb = new StringBuilder("All micro services health status: \n");
        List<ServiceInstance> instances = this.getServiceInstances();
        for (ServiceInstance si : instances) {
            EurekaDiscoveryClient.EurekaServiceInstance esi = (EurekaDiscoveryClient.EurekaServiceInstance) si;
            InstanceInfo info = esi.getInstanceInfo();
            sb.append(info.getAppName());
            sb.append("----");
            sb.append(info.getInstanceId());
            sb.append("----");
            sb.append(info.getStatus());
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<ServiceInstance> getServiceInstances() {
        List<String> ids = discoveryClient.getServices();
        List<ServiceInstance> result = new ArrayList<ServiceInstance>();
        for (String id : ids) {
            List<ServiceInstance> ins = discoveryClient.getInstances(id);
            result.addAll(ins);
        }
        return result;
    }
}
