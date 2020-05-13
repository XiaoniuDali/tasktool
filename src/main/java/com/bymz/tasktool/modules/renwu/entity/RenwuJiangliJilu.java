package com.bymz.tasktool.modules.renwu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bymz.tasktool.common.dict.Dict;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tt_renwu_jiangli_jilu")
public class RenwuJiangliJilu implements Serializable {
    @TableId
    private long id;

    private long renwuId;//任务id
    private long accountId;//执行人id
    private int jiangliWzlx;//奖励物质类型
    private double jiangliShuliang;//奖励数量
    private String jiangliMiaoshu;//奖励描述
    @Dict(key = "sys_yes_no")
    private int shifouLingqu;//是否领取奖励
    private Date lingquShijian;//领取时间
}
