package cn.com.jonpad.providesales.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品/库存
 * @author Jon Chan
 * @date 2019/1/23 17:21
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "t_commodity")
public class Commodity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  /**
   * 库存
   */
  Long amount;
  BigDecimal price;
  /**
   * 商品名称
   */
  String name;
  /**MM月dd日
   * 开始销售时间
   */
  @JSONField(format = "MM月dd日 hh:mm:ss")
  Date beginTime;
  /**
   * 结束销售时间
   */
  @JSONField(format = "MM月dd日 hh:mm:ss")
  Date endTime;
  /**
   * 折扣价
   */
  @Column(name = "discount_price")
  BigDecimal discountPrice;

  @Version
  private Long version;

  public Commodity() {
  }

  public Commodity(Long id, Long amount, BigDecimal price, String name, Date beginTime, Date endTime, BigDecimal discountPrice) {
    this.id = id;
    this.amount = amount;
    this.price = price;
    this.name = name;
    this.beginTime = beginTime;
    this.endTime = endTime;
    this.discountPrice = discountPrice;
  }
}
