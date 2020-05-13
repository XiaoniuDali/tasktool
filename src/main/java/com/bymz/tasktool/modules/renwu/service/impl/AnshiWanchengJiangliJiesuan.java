package com.bymz.tasktool.modules.renwu.service.impl;

import com.bymz.tasktool.modules.api.impl.BaseRenwuJiangliJiesuan;
import com.bymz.tasktool.modules.renwu.entity.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 按时完成任务奖励结算
 */
@Component
public class AnshiWanchengJiangliJiesuan extends BaseRenwuJiangliJiesuan {
    @Override
    public Object doJiangliJiesuan(RenwuXinxi renwuXinxi, RenwuChuli renwuChuli, RenwuJiangli jiangli, RenwuJiangliShezhi jiangliShezhi) {
//
//        Date yjJssj = renwuXinxi.getYjJssj().getTime();
//        Date wcsj = renwuChuli.getWanchengShijian();
//

        //计算是否有做到按时完成任务
        boolean shifouAnshiWancheng = renwuXinxi.getYjJssj().getTime()  >= renwuChuli.getWanchengShijian().getTime();

        //如果有做到按时完成任务要求
        if(shifouAnshiWancheng){
            //创建一个按时完成任务奖励记录
            RenwuJiangliJilu jiangliJilu = new RenwuJiangliJilu();
            jiangliJilu.setAccountId(renwuChuli.getAccountId());
            jiangliJilu.setJiangliMiaoshu("按时完成“"+ renwuXinxi.getRenwuMingcheng() +"”而获得的奖励！");

            //计算奖励数量
            double jiangliShuLiang = 0;
            //如果奖励数量是根据任务的完成度来计算
            if(jiangliShezhi.getShifouAwcdlq() == 1){
                int wcd = renwuChuli.getJdrPingfen();
                double bilv =  wcd / 100;
                jiangliShuLiang = jiangliShezhi.getJiangliShuliang() * bilv;
            }else {
                jiangliShuLiang = jiangliShezhi.getJiangliShuliang();
            }
            jiangliJilu.setJiangliShuliang(jiangliShuLiang);

            jiangliJilu.setJiangliWzlx(jiangliShezhi.getJiangliWzlx());
            jiangliJilu.setLingquShijian(new Date());
            jiangliJilu.setRenwuId(renwuXinxi.getId());
            jiangliJiluDao.insert(jiangliJilu);
        }
        return null;
    }


}
