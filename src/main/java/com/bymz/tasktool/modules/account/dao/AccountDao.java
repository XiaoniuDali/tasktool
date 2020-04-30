package com.bymz.tasktool.modules.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bymz.tasktool.modules.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDao extends BaseMapper<Account> {
    Account queryByUserName(String username);
}
