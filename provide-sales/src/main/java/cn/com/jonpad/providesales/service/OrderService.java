package cn.com.jonpad.providesales.service;

import cn.com.jonpad.providesales.controller.exception.InventoryAmountException;
import cn.com.jonpad.providesales.entity.Commodity;
import cn.com.jonpad.providesales.entity.Order;
import cn.com.jonpad.providesales.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单相关操作
 * @author Jon Chan
 * @date 2019/2/1 16:54
 */
@Service
public class OrderService {

  @Autowired
  OrderRepository orderRepository;
  @Autowired
  CommodityService commodityService;

  /**
   * 添加订单
   * @param orderInfo
   */
  @Transactional(rollbackFor = Exception.class)
  public void addOrder(Order orderInfo){
    Commodity commodity = commodityService.checkAmount(orderInfo.getCommodity(),orderInfo.getAmount());
    // 更新库存
    commodity.setAmount(commodity.getAmount() - orderInfo.getAmount() );

    commodityService.saveAndFlush(commodity);
    orderRepository.save(orderInfo);
  }
}
