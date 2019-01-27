package cn.com.jonpad.providesales.controller;

import cn.com.jonpad.providesales.entity.Commodity;
import cn.com.jonpad.providesales.service.CommodityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jon Chan
 * @date 2019/1/27 22:59
 */
@RestController
@RequestMapping("commodity")
public class CommodityController {

  @Autowired
  CommodityService commodityService;

  @GetMapping
  public ResponseEntity<List<Commodity>> getAll(){
    return ResponseEntity.ok(commodityService.findAll());
  }

  @GetMapping("{id}")
  @ApiOperation(value = "获取一个商品", notes = "获取一个商品")
  public ResponseEntity<Commodity> getOne(
    @ApiParam("商品ID")
    @PathVariable("id") Long id
  ){
    return ResponseEntity.ok(commodityService.getOne(id));
  }
}
