package com.bymz.tasktool.modules.renwu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("tt_renwu_jiangli")
public class RenwuJiangli implements Serializable {
    @TableId
    private long id;
    private long renwuId;
    private long renwuJlszId;//任务奖励设置id
    public int getRenwuJlszId() {
        return 0;
    }
}
