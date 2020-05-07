package com.bymz.tasktool.modules.sys.dict.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysDictDataDao extends BaseMapper<SysDictData> {
    List<SysDictData> queryByDictType(String dictType);
}
