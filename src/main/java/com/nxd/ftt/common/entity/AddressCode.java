package com.nxd.ftt.common.entity;

import com.google.gson.Gson;

import java.util.Date;

/**
 * AddressCode
 *
 * @author luhangqi
 * @date 2018/10/26
 */
public class AddressCode {

    /**
     * ID
     */
    private Integer id;

    /**
     * 区域代码
     */
    private String code;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 城乡分类代码
     */
    private String type;

    /**
     * 等级 1:省 2:市 3:县 4:镇 5:村
     */
    private Integer level;

    /**
     * 所属区域
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createor;

    /**
     * 修改人
     */
    private String updateor;


    /** ID
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /** 区域代码
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /** 区域名称
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /** 等级 1:省 2:市 3:县 4:镇 5:村
     *
     * @return level
     */
    public Integer getLevel() {
        return level;
    }

    /** 所属区域
     *
     * @return parentId
     */
    public Integer getParentId() {
        return parentId;
    }

    /** 创建时间
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /** 修改时间
     *
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /** 创建人
     *
     * @return createor
     */
    public String getCreateor() {
        return createor;
    }

    /** 修改人
     *
     * @return updateor
     */
    public String getUpdateor() {
        return updateor;
    }


    /**
     * ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 区域代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 区域名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 等级 1:省 2:市 3:县 4:镇 5:村
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 所属区域
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建人
     */
    public void setCreateor(String createor) {
        this.createor = createor;
    }

    /**
     * 修改人
     */
    public void setUpdateor(String updateor) {
        this.updateor = updateor;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}