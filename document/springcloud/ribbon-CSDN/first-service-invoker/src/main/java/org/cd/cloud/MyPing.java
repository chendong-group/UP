package org.cd.cloud;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;

/**
 * @classname: MyPing
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-14 22:10
 */
public class MyPing implements IPing {

    @Override
    public boolean isAlive(Server server) {
        System.out.println("自定义Ping，服务器信息：" + server.getHostPort());
        //为了方便演示，永远返回true，即服务器一直是正常状态
        return true;
    }

}
