package com.bymz.tasktool.modules.renwu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bymz.tasktool.comon.utils.PageData;
import com.bymz.tasktool.comon.utils.PageFactory;
import com.bymz.tasktool.modules.renwu.dao.RenwuJiangliShezhiDao;
import com.bymz.tasktool.modules.renwu.entity.RenwuJiangliShezhi;
import com.bymz.tasktool.modules.renwu.service.RenwuJiangliShezhiService;
import com.bymz.tasktool.modules.sys.dict.entity.SysDictData;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RenwuJiangliShezhiServiceImpl extends ServiceImpl<RenwuJiangliShezhiDao, RenwuJiangliShezhi> implements RenwuJiangliShezhiService {
    @Override
    public PageData queryPage(Map<String, Object> params) {
        String dictType = (String)params.get("dict_type");
        try {

            IPage<RenwuJiangliShezhi> page = new PageFactory<RenwuJiangliShezhi>().getPage(params, "tree_sort", true);
//            QueryWrapper<RenwuJiangliShezhi> queryWrapper = new QueryWrapper<RenwuJiangliShezhi>();
////            queryWrapper.like(StringUtils.isNotBlank(username), "username", username);
//            queryWrapper.eq(StringUtils.isNotBlank(dictType), "dict_type", dictType);

//            page = this.page(page , queryWrapper);
            page = this.page(page);
            return new PageData(page);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
