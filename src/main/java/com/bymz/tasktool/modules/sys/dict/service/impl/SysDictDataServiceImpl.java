package com.bymz.tasktool.modules.sys.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.sys.dict.service.SysDictDataService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataDao, SysDictData> implements SysDictDataService {

    @Override
    @CacheEvict(value = "dictCache", key = "#entity.dictType")
    public boolean save(SysDictData entity) {
        return super.save(entity);
    }

    @Override
    @CacheEvict(value = "dictCache", key = "#entity.dictType")
    public boolean updateById(SysDictData entity) {
        return super.updateById(entity);
    }

    @Override
    @Cacheable(value = "dictCache", key = "#dictType")
    public List<SysDictData> queryByDictType(String dictType) {
        System.out.println("------------------------run queryByDictType fun----------------------------------------");
        return baseMapper.queryByDictType(dictType);
    }
}
