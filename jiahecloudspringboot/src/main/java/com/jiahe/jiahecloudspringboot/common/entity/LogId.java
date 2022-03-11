package com.jiahe.jiahecloudspringboot.common.entity;

public class LogId {

    private Long id;
    private String logId;
    private String logPwd;
    private String logName;
    private Integer secclass;
    private Integer personnelType;
    private String pid;
    private String deptCode;
    private String deptName;
    private String unitCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogPwd() {
        return logPwd;
    }

    public void setLogPwd(String logPwd) {
        this.logPwd = logPwd;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public Integer getSecclass() {
        return secclass;
    }

    public void setSecclass(Integer secclass) {
        this.secclass = secclass;
    }

    public Integer getPersonnelType() {
        return personnelType;
    }

    public void setPersonnelType(Integer personnelType) {
        this.personnelType = personnelType;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }
}
