package cn.com.jonpad.providesales.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 库存不足错误
 * @author Jon Chan
 * @date 2019/2/1 17:28
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InventoryAmountException extends RuntimeException {
}
