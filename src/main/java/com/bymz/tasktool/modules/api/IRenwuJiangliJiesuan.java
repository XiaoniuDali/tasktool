package com.bymz.tasktool.modules.api;

import com.bymz.tasktool.modules.renwu.entity.RenwuJiangli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;
import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;
import com.bymz.tasktool.modules.renwu.entity.RenwuXinxi;

public interface IRenwuJiangliJiesuan {
    public Object doJiangliJiesuan(RenwuXinxi renwuXinxi, RenwuChuli renwuChuli, RenwuJiangli jiangli, RenwuJiangliShezhi jiangliShezhi);
}
