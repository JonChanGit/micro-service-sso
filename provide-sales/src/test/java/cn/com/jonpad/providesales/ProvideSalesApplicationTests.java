package cn.com.jonpad.providesales;

import cn.com.jonpad.providesales.entity.Order;
import cn.com.jonpad.providesales.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProvideSalesApplicationTests {

  @Autowired
  OrderService orderService;

  @Autowired
  RabbitTemplate rabbitTemplate;

  /**
   * 管理消息中间件
   */
  @Autowired
  AmqpAdmin admin;

  @Test
  public void contextLoads() {
  }

  @Test
  public void addOrder() {
    orderService.addOrder(new Order().setCommodity(8L).setAmount(5L));
  }

  /**
   * 单播
   */
  @Test
  public void rabbitDirectSend() {
    Map<String,Object> map = new HashMap<>(4);
    map.put("msg","hello");
    map.put("array", Arrays.asList("abc",1,2,true));
    rabbitTemplate.convertAndSend("exchange.direct","cn.com.jonpad",map);

  }
}

