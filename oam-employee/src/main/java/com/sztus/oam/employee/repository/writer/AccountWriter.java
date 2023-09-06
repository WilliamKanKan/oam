package com.sztus.oam.employee.repository.writer;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.sztus.oam.employee.object.domain.Account;
import com.sztus.oam.employee.repository.mapper.AccountMapper;
import org.springframework.stereotype.Repository;

@Repository
@DS("writer")
public class AccountWriter extends MPJBaseServiceImpl<AccountMapper, Account> {
}
