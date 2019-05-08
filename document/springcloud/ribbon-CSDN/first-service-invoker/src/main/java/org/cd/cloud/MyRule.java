package org.cd.cloud;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * @classname: MyRule
 * @description:
 * @author: Danny Chen
 * @create: 2019-03-14 22:10
 */
public class MyRule implements IRule {

    private ILoadBalancer loadBalancer;

    @Override
    public Server choose(Object o) {
        List<Server> servers = loadBalancer.getAllServers();
        System.out.println("自定义服务器规则，以下是服务器信息：");
        for (Server server : servers) {
            System.out.println(server.getHostPort());
        }
        //默认情况下，Ribbon的负载均衡规则会轮换着返回不同的服务器实例
        //这里为了方便演示自定义规则，我们指定每次都返回第一个服务器实例
        return servers.get(0);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer iLoadBalancer) {
        this.loadBalancer = iLoadBalancer;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.loadBalancer;
    }
}
