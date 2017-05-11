package com.syq.dynamic.gateway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syq.dynamic.gateway.event.RefreshRouteService;
import com.syq.dynamic.gateway.zuul.CustomRouteLocator;

import java.util.Map;
@RestController
public class DemoController {
	public final static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    RefreshRouteService refreshRouteService;

    @RequestMapping("/refreshRoute")
    public String refreshRoute(){
        refreshRouteService.refreshRoute();
        return "refreshRoute";
    }

    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping("/watchNowRoute")
    public String watchNowRoute(){
        //可以用debug模式看里面具体是什么
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        logger.info(handlerMap.toString());
        return "watchNowRoute";
    }



}
