package cn.com.jonpad.providesales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单
 * @author Jon Chan
 * @date 2019/1/23 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_order")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  @Column(name = "create_time")
  Date createTime;

  /**
   * 保存着商品的id
   */
  Long commodity;

  /**
   * 收货地址
   */
  String address;
  /**
   * 下单数量
   */
  Long amount;
}
