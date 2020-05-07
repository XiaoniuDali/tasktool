package com.bymz.tasktool.modules.sys.dict.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.bymz.tasktool.common.dict.Dict;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//import io.renren.common.validator.group.AddGroup;
//import io.renren.common.validator.group.UpdateGroup;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_dict_data")
public class SysDictData implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private String dictCode;

    private String parentCode;
    private String parentCodes;
    private double treeSort;
    private String treeSorts;
    private String treeLeaf;
    private double treeLevel;
    private String treeNames;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private String isSys;
    private String description;
    private String cssStyle;
    private String cssClass;
    private String status;
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String remarks;
    private String corpCode;
    private String corpName;


}
