package com.bymz.tasktool.modules.renwu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bymz.tasktool.common.dict.Dict;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("tt_renwu_xinxi")
public class RenwuXinxi implements Serializable {
    @TableId
    private long id;//任务id
    private String renwuMingcheng;//任务名称
    private String renwuMubiao;//任务目标
    private int renwuNdxs;//任务难度系数
    private int renwuLeixing;//任务类型

    @Dict(key = "sys_yes_no")
    private int sfwGzrw;//是否为工作任务

    @Dict(key = "sys_yes_no")
    private int sfwDsrw;//是否为定时任务
    private long sjrwId;//上级任务id
    private int yxj;//优先级
    private int dcld;//敦促力度大小
    private Date yjKssj;//预计开始时间
    private Date yjJssj;//预计结束时间
    @Dict(key = "sys_yes_no")
    private int sfyxDrzx;//是否允许多人执行
    @Dict(key = "sys_yes_no")
    private int shifouZjzdZxr;//是否直接指定执行人
    private int zhuangtai;//任务状态
    private long jianduren;//任务监督人
    private long renwuCjr;//任务创建人id
    private Date renwuCjsj;//任务创建时间
}
