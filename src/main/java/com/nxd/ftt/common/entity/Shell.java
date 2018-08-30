package com.nxd.ftt.common.entity;

/**
 * Regedit
 *
 * @author luhangqi
 * @date 2018/8/30
 */
public class Shell {

    /**
     * 0：失败，1：成功
     */
    private int status;

    private String data;

    public Shell() {
    }

    public Shell(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
