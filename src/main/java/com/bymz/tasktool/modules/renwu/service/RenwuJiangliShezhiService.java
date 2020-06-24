package com.bymz.tasktool.modules.renwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bymz.tasktool.comon.utils.PageData;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;

import java.util.Map;

public interface RenwuJiangliShezhiService extends IService<RenwuJiangliShezhi> {
    PageData queryPage(Map<String, Object> params);
}
