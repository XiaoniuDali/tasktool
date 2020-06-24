package com.bymz.tasktool.modules.renwu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bymz.tasktool.common.dict.Dict;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务处理信息
 */
@Data
@TableName("tt_renwu_chuli")
public class RenwuChuli implements Serializable {
    @TableId
    private long id;

    private long renwuId;//任务id
    private long accountId;//指定接受人id
    @Dict(key = "sys_yes_no")
    private int shifouJsrw;//是否接受任务
    private Date jieshouShijian;//接受时间
    private Date kaishiRenwuShijian;//开始任务时间
    private Date yujiWanchengShijian;//预计完成时间，等于开始任务时间加上完成任务预计所需时间
    private int jindu;//个人成进度
    private Date wanchengShijian;//完成时间
    private int ziwoPingfen;//自我评分
    private Date querenWcsj;//监督人确认完成时间
    private int jdrPingfen;//监督人评分

}
