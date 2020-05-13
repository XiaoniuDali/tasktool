package com.bymz.tasktool.modules.renwu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bymz.tasktool.common.dict.Dict;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tt_renwu_jlsz")
public class RenwuJiangliShezhi implements Serializable {
    @TableId
    private long id;
    private long accountId;
    private String jiangliLiyou;//奖励理由
    private String jiangliTiaojian;//奖励条件
    private int jiangliWzlx;//奖励物质类型
    private int jiangliWzdw;//奖励物质单位
    private int jiangliShuliang;//奖励物质数量
    @Dict(key = "sys_yes_no")
    private int shifouAwcdlq;//是否按完成度领取
    private int duixianFangshi;//奖励兑现方式  有系统自动兑现、用户手动兑现
    private String jiesuanClassName;//结算类的类全名
    private String jiangliMiaoshu;//奖励描述

}
