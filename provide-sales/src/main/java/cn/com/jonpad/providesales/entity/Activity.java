package cn.com.jonpad.providesales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动
 * @author Jon Chan
 * @date 2019/1/23 17:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_activity")
public class Activity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  /**
   * 商品Id
   */
  Long commodityId;

  /**
   * 商品对象
   */
  @Transient
  Commodity commodity;

  /**
   * 开始时间
   */
  @Column(name = "start_time")
  Date startTime;

  /**
   * 开始时间
   */
  @Column(name = "end_time")
  Date endTime;

  /**
   * 活动可用
   */
  Boolean enable;
}
