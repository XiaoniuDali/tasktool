package com.bymz.tasktool.modules.sys.dict.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;

import java.util.List;

public interface SysDictDataService extends IService<SysDictData> {
    List<SysDictData> queryByDictType(String dictType);
}
