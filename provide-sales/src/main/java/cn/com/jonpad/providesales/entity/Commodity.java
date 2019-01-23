package cn.com.jonpad.providesales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 商品/库存
 * @author Jon Chan
 * @date 2019/1/23 17:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
