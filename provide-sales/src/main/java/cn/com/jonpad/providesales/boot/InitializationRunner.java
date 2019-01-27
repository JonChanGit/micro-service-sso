package cn.com.jonpad.providesales.boot;

import cn.com.jonpad.providesales.entity.Account;
import cn.com.jonpad.providesales.entity.Commodity;
import cn.com.jonpad.providesales.repository.AccountRepository;
import cn.com.jonpad.providesales.repository.CommodityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Jon Chan
 * @date 2019/1/27 16:06
 */
@Slf4j
@Component
@Order(1)
public class InitializationRunner implements CommandLineRunner {
  @Autowired
  CommodityRepository cr;
  @Autowired
  AccountRepository ar;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void run(String... args) throws Exception {
    BigDecimal zero = new BigDecimal("0");
    BigDecimal one = new BigDecimal("1");
    BigDecimal oneHundred = new BigDecimal("100");

    if(cr.count() <= 0){
      List<Commodity> commodityList = new ArrayList<>(8);
      Instant now = Instant.now();
      Commodity c = new Commodity(1L, 100L,one,"商品1", Date.from(now), Date.from(now.plus(1L, ChronoUnit.DAYS)), zero);
      commodityList.add(c);
      c = new Commodity(1L, 100L, one,"商品1", Date.from(now.plus(1L, ChronoUnit.DAYS)), Date.from(now.plus(2L, ChronoUnit.DAYS)), zero);
      commodityList.add(c);
      c = new Commodity(2L, 100L, one,"商品1", Date.from(now.plus(2L, ChronoUnit.DAYS)), Date.from(now.plus(3L, ChronoUnit.DAYS)), zero);
      commodityList.add(c);
      c = new Commodity(3L, 100L, one,"商品1", Date.from(now.plus(3L, ChronoUnit.DAYS)), Date.from(now.plus(4L, ChronoUnit.DAYS)), zero);
      commodityList.add(c);
      c = new Commodity(4L, 100L, one,"商品1", Date.from(now.plus(4L, ChronoUnit.DAYS)), Date.from(now.plus(5L, ChronoUnit.DAYS)), zero);
      commodityList.add(c);

      cr.saveAll(commodityList);
    }

    Account account = new Account(1L, oneHundred, 1L);
    ar.saveAndFlush(account);

  }
}
