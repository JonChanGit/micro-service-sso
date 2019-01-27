package cn.com.jonpad.providesales.controller;

import cn.com.jonpad.providesales.entity.Account;
import cn.com.jonpad.providesales.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
