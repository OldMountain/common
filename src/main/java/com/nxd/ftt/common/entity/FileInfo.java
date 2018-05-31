package com.nxd.ftt.common.entity;

import com.google.gson.Gson;
import com.nxd.ftt.common.util.FTools;

import java.util.Date;

/**
 * FileInfo
 *
 * @author luhangqi
 * @date 2018/05/31
 */
public class FileInfo {

    /**
     *
     */
    private Integer id;

    /**
     * 关联id
     */
    private String linkId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件大小
     */
    private Integer fileSize;

    private String fileSizeStr;

    /**
     * 保存路径
     */
    private String filePath;

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

    /**
     * 扩展名
     */
    private String etx;

    /**
     * 备注
     */
    private String remark;


    public FileInfo() {
    }

    public FileInfo(String fileName, Integer fileSize, String filePath, String etx) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.etx = etx;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 关联id
     *
     * @return linkId
     */
    public String getLinkId() {
        return linkId;
    }

    /**
     * 文件名称
     *
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件大小
     *
     * @return fileSize
     */
    public Integer getFileSize() {
        return fileSize;
    }

    /**
     * 保存路径
     *
     * @return filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 创建时间
     *
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 修改时间
     *
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 创建人
     *
     * @return createor
     */
    public String getCreateor() {
        return createor;
    }

    /**
     * 修改人
     *
     * @return updateor
     */
    public String getUpdateor() {
        return updateor;
    }

    /**
     * 扩展名
     *
     * @return etx
     */
    public String getEtx() {
        return etx;
    }

    /**
     * 备注
     *
     * @return remark
     */
    public String getRemark() {
        return remark;
    }


    /**
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 关联id
     */
    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    /**
     * 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 文件大小
     */
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 保存路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    /**
     * 扩展名
     */
    public void setEtx(String etx) {
        this.etx = etx;
    }

    /**
     * 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getFileSizeStr() {
        return fileSize != null ? FTools.getFileSize((long) fileSize) : "0";
    }

    public void setFileSizeStr(String fileSizeStr) {
        this.fileSizeStr = fileSizeStr;
    }
}