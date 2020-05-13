package com.bymz.tasktool.modules.sys.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bymz.tasktool.comon.utils.PageData;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;

import java.util.List;
import java.util.Map;

public interface SysDictDataService extends IService<SysDictData> {
    List<SysDictData> queryByDictType(String dictType);

    public PageData queryPage(Map<String, Object> params);
}
