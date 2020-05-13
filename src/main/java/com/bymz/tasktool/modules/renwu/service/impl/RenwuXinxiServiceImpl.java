package com.bymz.tasktool.modules.renwu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bymz.tasktool.comon.utils.SpringUtil;
import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliShezhiDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuXinxiDao;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;
import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;
import com.bymz.tasktool.modules.renwu.entity.RenwuXinxi;
import com.bymz.tasktool.modules.api.IRenwuJiangliJiesuan;
import com.bymz.tasktool.modules.renwu.service.RenwuXinxiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("renwuXinxiService")
public class RenwuXinxiServiceImpl extends ServiceImpl<RenwuXinxiDao, RenwuXinxi> implements RenwuXinxiService {

    private RenwuXinxiDao dao;
    //    @Autowired
    private RenwuJiangliDao jiangliDao;
//    @Autowired
    private RenwuJiangliShezhiDao jiangliShezhiDao;

    @Override
    public Map<String, Object> checkNewRenwu() {
        return null;
    }

    @Override
    public Object addRenwu(RenwuXinxi renwuXinxi) {
        //检查新增任务
        checkNewRenwu();

        //保存新增任务到数据库
        super.baseMapper.insert(renwuXinxi);
        //发送消息给任务候选人
        {
            //找出所有的候选执行人
            List<Object> hxzxrList = new ArrayList<>();

            if(true){//如果选择了某个或多个群作为候选执行人
                //获取所有群成员，并将其保存到hxzxrList集合中

            }

            if(true){//如果选择了具体的一个或多个执行人的id
                //根据具体的候选执行人id查找出候选执行人信息，并将其保存到hxzxrList集合中

            }

            //发送消息给所有任务候选人
            for(Object hxzxr : hxzxrList){
                if(true){//如果候选人是创建人

                }else{
                    //发送消息给候选执行人



                }
            }

        }


        return null;
    }

    @Override
    public Object jieshouRenwu(int renwuId, int accountId) {
        //根据任务id查询出任务信息
        RenwuXinxi renwuXinxi = null;

        //如果任务允许多人执行
        if(true){
            //创建任务接受对象，并保存到数据库
            RenwuChuli renwuChuli = new RenwuChuli();

            //将任务接受对象保存到数据库
        }else{
            //查询任务是否已经被人接收
            boolean sfbjs = true;
            if(sfbjs){
                //返回不能任务已经被其他人接收的消息
                return null;
            }else{
                //创建任务接受对象，并保存到数据库
                RenwuChuli renwuChuli = new RenwuChuli();

                //将任务接受对象保存到数据库
            }

        }
        return null;
    }

    @Override
    public Object wanchengRenwu(int renwuId, int accountId, int pingfen) {
        //根据任务id查询出任务信息
        RenwuXinxi renwuXinxi = null;

        //根据任务id和执行人id查询得到用户接受任务信息
        RenwuChuli renwuChuli = null;

        //修改任务接受信息，包括任务完成时间、自我评分
        if(true){//如果任务监督人是自己
           //修改任务接受信息，包括监督人确认完成时间、监督人评分
            shouyiJiesuan(renwuId, accountId);
        }else{

        }
        return null;
    }

    @Override
    public Object shouyiJiesuan(int renwuId, int accountId) {

        return null;
    }


}
