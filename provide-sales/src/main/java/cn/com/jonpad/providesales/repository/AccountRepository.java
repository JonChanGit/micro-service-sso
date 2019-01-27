package cn.com.jonpad.providesales.repository;

import cn.com.jonpad.providesales.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * AccountRepository
 * @author Jon Chan
 * @date 2019/1/23 17:38
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>, QuerydslPredicateExecutor<Account> {
}
