package cn.com.jonpad.providesales.repository;

import cn.com.jonpad.providesales.entity.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * 商品Repository
 * @author Jon Chan
 * @date 2019/1/23 17:38
 */
@Repository
public interface CommodityRepository extends JpaRepository<Commodity, Long>, QuerydslPredicateExecutor<Commodity> {
}
