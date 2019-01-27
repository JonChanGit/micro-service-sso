package cn.com.jonpad.providesales.service;

import cn.com.jonpad.providesales.entity.Account;
import cn.com.jonpad.providesales.entity.QAccount;
import cn.com.jonpad.providesales.repository.AccountRepository;
import com.querydsl.core.types.Predicate;
import cn.com.jonpad.api.controller.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Jon Chan
 * @date 2019/1/27 16:56
 */
@Service
public class AccountService {

  @Autowired
  AccountRepository accountRepository;

  public Account getOne(Long userId){
    QAccount qAccount = QAccount.account;
    Predicate predicate = qAccount.userId.eq(userId);
    Optional<Account> one = accountRepository.findOne(predicate);
    if (one.isPresent()) {
      return one.get();
    }else {
      throw new NotFoundException();
    }
  }
}
