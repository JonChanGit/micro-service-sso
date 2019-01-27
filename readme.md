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


## RESTful API 设计指南

[RESTful API 设计指南](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)

### 本工程中遵循的指南
>  参考`provide-sales`工程

### 编码规划
#### Java
```java
package cn.com.jonpad.providesales.controller;
import org.springframework.http.ResponseEntity;

/**
 * @author Jon Chan
 * @date 2019/1/27 16:54
 */
@RestController
@RequestMapping("account")
public class AccountController {

  @Autowired
  AccountService accountService;

  @GetMapping("{id}")
  @ApiOperation(value = "获取一个用户的账号", notes = "获取一个用户的账号")
  public ResponseEntity<Account> getOne(
    @ApiParam("搜索关键字")
    @PathVariable("id") Long userId
  ){
    return ResponseEntity.ok(accountService.getOne(userId));
  }
}

```
1. 使用 `@RestController` 和 `@RequestMapping("account")`标记入口类
2. 使用[文档【五、HTTP动词、六、过滤信息（Filtering）】）](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)中的要求设计接口访问方式和参数传递方式
3. 使用Exception抛出异常，而不要手动返回异常描述，如下：
```java
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {}
```
```java
package cn.com.jonpad.providesales.service;

@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  public Account getOne(Long userId){
    QAccount qAccount = QAccount.account;
    Predicate predicate = qAccount.userId.eq(userId);
    Optional<Account> one = accountRepository.findOne(predicate);
    if (one.isPresent()) {
      return one.get();
    }else {
      throw new NotFoundException();
    }
  }
}
```
> `NotFoundException`中使用`@ResponseStatus`注解，并且规定了返回值`HttpStatus`,`HttpStatus`取值请参照文档[文档【七、状态码（Status Codes）】）](http://www.ruanyifeng.com/blog/2014/05/restful_api.html)中的要求设置
此时使用HTTP访问将会返回：
```http response
Http Status : 404 Not Found
```

#### JavaScript
1. 判断返回状态码
1. 按照规定判断，并且与接口约定各个状态码都代表什么返回提示，在前端代码中写入提示
1. 如： 
```javascript
/**
 * 获取账户信息
 * @param userId
 */
export function getAccount(userId) {
  return request({
    url: `${SALES_SERVICE}/account/${userId}`,
    method: 'get',
    errorDefined: {
      404: `用户未找到`
    }
  })
}
```
```javascript
let msg = '操作失败，请重试'
if (error.response && error.response.status) {
  if (error.config.errorDefined) {
    msg = error.config.errorDefined[error.response.status]
  }
}
MessageBox.alert(`${msg}`)
```
