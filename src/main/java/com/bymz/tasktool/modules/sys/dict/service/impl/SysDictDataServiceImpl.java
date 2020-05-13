package com.bymz.tasktool.modules.sys.dict.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.bymz.tasktool.comon.utils.PageData;
import com.bymz.tasktool.comon.utils.PageFactory;
import com.bymz.tasktool.modules.sys.dict.dao.SysDictDataDao;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import com.bymz.tasktool.modules.sys.dict.service.SysDictDataService;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageData queryPage(Map<String, Object> params) {
        String dictType = (String)params.get("dict_type");
        try {

            IPage<SysDictData> page = new PageFactory<SysDictData>().getPage(params, "tree_sort", true);
            QueryWrapper<SysDictData> queryWrapper = new QueryWrapper<SysDictData>();
//            queryWrapper.like(StringUtils.isNotBlank(username), "username", username);
            queryWrapper.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);

            page = this.page(page , queryWrapper);
            return new PageData(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
