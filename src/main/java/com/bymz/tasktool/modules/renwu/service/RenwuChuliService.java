package com.bymz.tasktool.modules.renwu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;

public interface RenwuChuliService extends IService<RenwuChuli> {

    /**
     * 接受任务
     * @param renwuId 任务id
     * @param accountId 用户id
     * @return
     */
    public Object jieshouRenwu(long renwuId, long accountId);

    /**
     * 拒绝任务
     * @param renwuId 任务id
     * @param accountId 用户id
     * @return
     */
    public Object jujueRenwu(long renwuId, long accountId);

    /**
     * 完成任务
     * @param renwuId 任务id
     * @param accountId 用户id
     * @return
     */
    public Object wanchengRenwu(long renwuId, long accountId, String wanchengShijian, int pingfen);

    /**
     * 任务监督人检查确定执行人完成任务后调用
     * @param renwuChuliId 任务id
     * @param accountId 用户id
     * @return
     */
    public Object querenWanchengRenwu(long renwuChuliId, long accountId, String wanchengShijian, int pingfen);

    /**
     * 放弃任务
     * @param renwuId 任务id
     * @param accountId 用户id
     * @return
     */
    public Object fangqiRenwu(long renwuId, long accountId);


}
