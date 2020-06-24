package com.bymz.tasktool.modules.renwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bymz.tasktool.modules.renwu.entity.RenwuXinxi;

import java.util.Map;

public interface RenwuXinxiService extends IService<RenwuXinxi> {

    public Map<String, Object> checkNewRenwu();

    public Object addRenwu(RenwuXinxi renwuXinxi);

    //候选执行人接受任务调用接口
    public Object jieshouRenwu(int renwuId, int accountId);

    //任务执行人完成任务后，调用该接口标识完成任务
    public Object wanchengRenwu(int renwuId, int accountId,int pingfen);

    //监督人判定任务完成后，调用该接口完成任务执行人的任务收益结算
    public Object shouyiJiesuan(int renwuId, int accountId);

    Object delRenwuAndXiangguan(Long id);
}
