### SpringCloud学习总结

#### Zuul服务网关

Zuul是SpirngCloud的网关入口。能够动态配置路由是高效使用SpringCloud的关键。

在学习过程中git+SpringCloud-Config可以进行动态修改配置文件，但是在生产环境是不允许修改配置文件的。

最理想的情况是将路由配置信息存储到数据库中，Zuul动态获取路由配置信息。

##### 动态Zuul路由配置流程

1. 通过数据库获取配置信息
2. 触发Zuul更新路由事件

##### 重点类

##### org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute

Zuul路由的实体bean，动态获取路由信息的关键

##### org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator

Zuul路由装载器，装载路由的配置信息ZuulRoute，我们自定义RouteLocator可以参考这个类

##### org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator

Zuul路由刷新事件接口，我们自定义的RouteLocator要实现这个接口用来处理刷新事件

##### com.syq.dynamic.gateway.zuul.CustomRouteLocator

自定义RouteLocator继承SimpleRouteLocator实现RefreshableRouteLocator，<a href="https://github.com/shinelon/springcloud/blob/master/gateway-dynamic-zuul/src/main/java/com/syq/dynamic/gateway/zuul/CustomRouteLocator.java">详细见代码</a>

##### com.syq.dynamic.gateway.event.RefreshRouteService

发送一个RoutesRefreshedEvent事件，刷新路由

#### 工程实现

1. 获取配置文件的route配置信息
2. 获取DB中的route配置信息
3. 暴露RoutesRefresh方法手动刷新路由
4. 整合到SpringCloud服务中


#### DB配置

<a href="https://github.com/shinelon/springcloud/blob/master/gateway-dynamic-zuul/src/main/resources/db/schema.sql">创建Sql</a>

#### 测试方法

顺序启动server-eureka，service-component，gateway-dynamic-zuul

修改DB路由数据 方法 /watchNowRoute 查看路由信息，方法/refreshRoute刷新路由信息


