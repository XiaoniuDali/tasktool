package com.bymz.tasktool.modules.renwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bymz.tasktool.comon.utils.SpringUtil;
import com.bymz.tasktool.modules.account.dao.AccountDao;
import com.bymz.tasktool.modules.account.entity.Account;
import com.bymz.tasktool.modules.api.IRenwuJiangliJiesuan;
import com.bymz.tasktool.modules.renwu.dao.RenwuChuliDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliShezhiDao;
import com.bymz.tasktool.modules.renwu.dao.RenwuXinxiDao;
import com.bymz.tasktool.modules.renwu.entity.RenwuChuli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangli;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;
import com.bymz.tasktool.modules.renwu.entity.RenwuXinxi;
import com.bymz.tasktool.modules.renwu.service.RenwuChuliService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*
任务处理业务
 */
@Service("renwuChuliService")
public class RenwuChuliServiceImpl extends ServiceImpl<RenwuChuliDao, RenwuChuli> implements RenwuChuliService {
    @Autowired
    private RenwuXinxiDao renwuXinxiDao;
    @Autowired
    private RenwuChuliDao renwuChuliDao;

    @Autowired
    private RenwuJiangliDao renwuJiangliDao;
    @Autowired
    private RenwuJiangliShezhiDao renwuJiangliShezhiDao;

    @Autowired
    private AccountDao accountDao;


    @Override
    public synchronized Object jieshouRenwu(long renwuId, long accountId) {
        RenwuXinxi renwuXinxi = renwuXinxiDao.selectById(renwuId);

        //如果任务不允许多人同时执行
        if(renwuXinxi.getSfyxDrzx() == 0){
            //判断任务是否已经被其他人接收了
            QueryWrapper<RenwuChuli> wrapper = new QueryWrapper<>();
            wrapper.eq(true, "renwu_id", renwuId);
            RenwuChuli renwuChuli = renwuChuliDao.selectOne(wrapper);

            //如果已经被其他人接收了任务
            if(null != renwuChuli){
                //提示任务以被其他人接收

            }else{
                //接收任务
                RenwuChuli entity = createRenwuChuli(renwuId, accountId, 1);
                renwuChuliDao.insert(entity);
            }

        }else{
            //接收任务
            RenwuChuli entity = createRenwuChuli(renwuId, accountId, 1);
            renwuChuliDao.insert(entity);
        }
        return null;
    }

    private RenwuChuli createRenwuChuli(long renwuId, long accountId, int sfJsrw){
        RenwuChuli renwuChuli = new RenwuChuli();
        renwuChuli.setRenwuId(renwuId);
        renwuChuli.setAccountId(accountId);
        renwuChuli.setShifouJsrw(sfJsrw);
        renwuChuli.setJieshouShijian(new Date());
        return renwuChuli;
    }


    @Override
    public Object jujueRenwu(long renwuId, long accountId) {
        return null;
    }

    @Override
    public Object kaishiRenwu(long renwuChuliId) {
        RenwuChuli renwuChuli = renwuChuliDao.selectById(renwuChuliId);
        RenwuXinxi renwuXinxi = renwuXinxiDao.selectById(renwuChuli.getRenwuId());

        {//修改任务处理信息
            renwuChuli.setKaishiRenwuShijian(new Date());

            //如果任务创建人有设置预计开始和结束时间，则计算预计完成时间
            if(renwuXinxi.getYjJssj() != null && renwuXinxi.getYjJssj() != null){
                long zxrwSxsj = renwuXinxi.getYjJssj().getTime() - renwuXinxi.getYjKssj().getTime();
                zxrwSxsj = renwuChuli.getKaishiRenwuShijian().getTime() + zxrwSxsj;

                Date yujiWanchengShijian = new Date();
                yujiWanchengShijian.setTime(zxrwSxsj);

                renwuChuli.setYujiWanchengShijian(yujiWanchengShijian);
            }

            renwuChuli.setJindu(0);
            //计算预计完成时间
            renwuChuliDao.updateById(renwuChuli);

        }
        return null;
    }

    @Override
    public Object wanchengRenwu(long renwuId, long accountId, String wanchengShijian, int pingfen) {
        //根据任务id查询出任务信息
        RenwuXinxi renwuXinxi = renwuXinxiDao.selectById(renwuId);
        //根据用户id查询出用户信息
        Account account = accountDao.selectById(accountId);

        //根据任务id和执行人id查询得到用户接受任务信息
        QueryWrapper<RenwuChuli> wrapper = new QueryWrapper<>();
        wrapper.eq(true, "renwu_id", renwuId);
        wrapper.eq(true, "account_id", accountId);
        RenwuChuli renwuChuli = renwuChuliDao.selectOne(wrapper);

        // 判断当前登入用户是否等于任务执行人，如果不是不准许操作
        if(false){
            return null;
        }

        renwuChuli.setJindu(100);
        renwuChuli.setZiwoPingfen(pingfen);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            renwuChuli.setWanchengShijian(format1.parse(wanchengShijian));
        }catch (Exception e){

        }

        //如果任务监督人等于任务执行人，调用确认完成任务方法
        if(renwuXinxi.getJianduren() == renwuChuli.getAccountId()){
            renwuChuli.setJdrPingfen(pingfen);
            try {
                renwuChuli.setQuerenWcsj(format1.parse(wanchengShijian));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            renwuChuliDao.updateById(renwuChuli);
            querenWanchengRenwu(renwuId, accountId, wanchengShijian, pingfen);
        }else{
            renwuChuliDao.updateById(renwuChuli);
        }

        return null;
    }

    @Override
    public Object querenWanchengRenwu(long renwuChuliId, long accountId, String wanchengShijian, int pingfen) {
        //根据任务处理id查询得到用户接受任务信息
        RenwuChuli renwuChuli = renwuChuliDao.selectById(renwuChuliId);
        Object principal = SecurityUtils.getSubject().getPrincipal();

        //判断当前用户登入用户是监督人，如果不是监督人，不允许调用处理
        if(false){

        }

        //根据任务id查询出任务信息
        RenwuXinxi renwuXinxi = renwuXinxiDao.selectById(renwuChuli.getRenwuId());
        //根据用户id查询出用户信息
        Account account = accountDao.selectById(accountId);


        //如果任务监督人不等于任务执行人，修改监督人评分和确认完成时间
        if(renwuXinxi.getJianduren() == renwuChuli.getAccountId()){
            renwuChuli.setJdrPingfen(pingfen);
            try {
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                renwuChuli.setQuerenWcsj(format1.parse(wanchengShijian));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            renwuChuliDao.updateById(renwuChuli);
        }


        //根据任务id查询出任务的所有奖励项目
        QueryWrapper<RenwuJiangli> renwuJiangliwrapper = new QueryWrapper<>();
        renwuJiangliwrapper.eq("renwu_id", renwuChuliId);
        List<RenwuJiangli> renwuJianglis = renwuJiangliDao.selectList(renwuJiangliwrapper);

        //结算每一项奖励
        for (RenwuJiangli jiangli : renwuJianglis){
            //根据任务奖励信息的任务奖励设置id找到任务奖励设置信息
            long jiangliShezhiId = jiangli.getRenwuJlszId();
            RenwuJiangliShezhi jiangliShezhi = renwuJiangliShezhiDao.selectById(jiangliShezhiId);
            String jiesuanClassName = jiangliShezhi.getJiesuanClassName();

            try {
                Class<?> jiesuanClass = Class.forName(jiesuanClassName);
                IRenwuJiangliJiesuan jiangliJiesuan = (IRenwuJiangliJiesuan) SpringUtil.getBean(jiesuanClass);
                jiangliJiesuan.doJiangliJiesuan(renwuXinxi, renwuChuli, jiangli,jiangliShezhi);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }


    @Override
    public Object fangqiRenwu(long renwuId, long accountId) {
        return null;
    }
}
