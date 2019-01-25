# 目标
> 集群秒杀
## 前端工程
> page/seckill

> 已配置反向代理到Zuul中

> 有同学知道如何使用gateway做服务网关，通过Security认证可以帮忙配置下
### UI框架
> [mint-ui](http://mint-ui.github.io/docs/#/zh-cn)


## 服务端启动顺序
1. config-server
	> 配置中心
	
	配置读取规则：
	
	`http://localhost:8000/sso/application-dev.yml`
	
	`http://localhost:8000/分支/应用名称-配置.yml`
1. eureka
	> 注册中心
1. zuul
1. oauth-jwt
1. provide-user
1. provide-sales