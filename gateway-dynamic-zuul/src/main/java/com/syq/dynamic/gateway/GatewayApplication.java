package com.syq.dynamic.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication {

    public static void main(String[] args) {
//    	 SpringApplication.run(GatewayApplication.class, args);
    	 new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
    }

}