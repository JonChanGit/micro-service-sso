package cn.com.jonpad.providesales.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jon Chan
 * @date 2019/1/17 0:28
 */
@RestController()
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String test(){
        return "Success";
    }
}
