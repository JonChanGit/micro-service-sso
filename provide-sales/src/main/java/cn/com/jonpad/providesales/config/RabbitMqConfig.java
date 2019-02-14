package cn.com.jonpad.providesales.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author Jon Chan
 * @date 2019/2/14 9:40
 */
@Configuration
public class RabbitMqConfig {
  /**
   * 自定义MessageConverter
   * @return
   */
  @Bean
  public MessageConverter messageConverter(){
    return new Jackson2JsonMessageConverter();
  }
}
