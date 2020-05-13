package com.bymz.tasktool.modules.api.impl;

import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliJiluDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliShezhiDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuXinxiDao;
import com.bymz.tasktool.modules.api.IRenwuJiangliJiesuan;

public abstract class BaseRenwuJiangliJiesuan implements IRenwuJiangliJiesuan {
    protected RenwuXinxiDao dao;
    //    @Autowired
    protected RenwuJiangliDao jiangliDao;
    //    @Autowired
    protected RenwuJiangliShezhiDao jiangliShezhiDao;

    //    @Autowired
    protected RenwuJiangliJiluDao jiangliJiluDao;
}
