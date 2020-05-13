package com.bymz.tasktool.comon.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bymz.tasktool.modules.security.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class PageFactory<T> {
    //分页参数
    private long curPage = 1;
    private long limit = 10;

//    public IPage<T> getPage(Map<String, Object> params) throws Exception{
//        return this.getPage(params, null, false);
//    }

    public IPage<T> getPage(Map<String, Object> params, String defaultOrderField, boolean isAsc) throws Exception{
        long tempCurPage = curPage;
        long tempLimit = limit;
        if(params.get(Constant.PAGE) != null){
            tempCurPage = Long.parseLong((String)params.get(Constant.PAGE));
        }
        if(params.get(Constant.LIMIT) != null){
            tempLimit = Long.parseLong((String)params.get(Constant.LIMIT));
        }

        //分页对象
        Page<T> page = new Page<>(tempCurPage, tempLimit);

        //分页参数
        params.put(Constant.PAGE, page);



        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = (String)params.get(Constant.ORDER_FIELD);
        orderField = SQLFilter.sqlInject(orderField);
        String order = (String)params.get(Constant.ORDER);

        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(Constant.ASC.equalsIgnoreCase(order)) {
                return page.setAsc(orderField);
            }else {
                return page.setDesc(orderField);
            }
        }

        //默认排序
        if(isAsc) {
            page.setAsc(defaultOrderField);
        }else {
            page.setDesc(defaultOrderField);
        }

        return page;
    }
}
