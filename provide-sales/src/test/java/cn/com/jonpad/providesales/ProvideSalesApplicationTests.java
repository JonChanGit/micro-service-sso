package cn.com.jonpad.providesales;

import cn.com.jonpad.providesales.entity.Order;
import cn.com.jonpad.providesales.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProvideSalesApplicationTests {

  @Autowired
  OrderService orderService;


  @Test
  public void contextLoads() {
  }

  @Test
  public void addOrder() {
    orderService.addOrder(new Order().setCommodity(8L).setAmount(5L));
  }

}

