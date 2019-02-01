package cn.com.jonpad.providesales.controller;

import cn.com.jonpad.providesales.entity.Order;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jon Chan
 * @date 2019/2/1 15:46
 */
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
  @PostMapping
  @ApiOperation(value = "下单", notes = "下单")
  @ResponseStatus(HttpStatus.CREATED)
  public void buyCommodity(
    @ApiParam("下单信息")
    @RequestBody Order order
    ){
    log.info("Order Information {}", order);
  }
}
