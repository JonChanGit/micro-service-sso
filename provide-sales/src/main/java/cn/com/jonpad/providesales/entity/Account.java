package cn.com.jonpad.providesales.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 账户（代表金融账号）
 * @author Jon Chan
 * @date 2019/1/27 16:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_account")
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  /**
   * 余额
   */
  BigDecimal balance;

  /**
   * 关联用户表
   */
  Long userId;
}
