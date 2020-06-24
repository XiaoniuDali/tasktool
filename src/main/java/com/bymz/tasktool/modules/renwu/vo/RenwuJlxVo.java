package com.bymz.tasktool.modules.renwu.vo;

import com.bymz.tasktool.modules.renwu.entity.RenwuJlx;

import java.util.ArrayList;

public class RenwuJlxVo {
    private Long renwuId;

    private ArrayList<RenwuJlx> jlxList;

    public Long getRenwuId() {
        return renwuId;
    }

    public void setRenwuId(Long renwuId) {
        this.renwuId = renwuId;
    }

    public ArrayList<RenwuJlx> getJlxList() {
        return jlxList;
    }

    public void setJlxList(ArrayList<RenwuJlx> jlxList) {
        this.jlxList = jlxList;
    }
}
