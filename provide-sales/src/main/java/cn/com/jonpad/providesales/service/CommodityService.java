package cn.com.jonpad.providesales.service;

import cn.com.jonpad.api.controller.exception.NotFoundException;
import cn.com.jonpad.providesales.entity.Commodity;
import cn.com.jonpad.providesales.repository.CommodityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jon Chan
 * @date 2019/1/27 23:07
 */
@Service
public class CommodityService {
  @Autowired
  CommodityRepository commodityRepository;
  public List<Commodity> findAll(){
    return commodityRepository.findAll();
  }

  public Commodity getOne(Long id) {
    Commodity one = commodityRepository.getOne(id);
    if (one == null){
      throw new NotFoundException();
    }
    return one;
  }
}
