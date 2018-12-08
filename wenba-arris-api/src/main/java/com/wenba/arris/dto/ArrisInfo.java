package com.wenba.arris.dto;


import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * arris_info
 * @author
 */
public class ArrisInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 表达式名称
     */
    private String arrisName;

    /**
     * 当前发布版本
     */
    private Integer arrisVers;

    /**
     * 表达式描述
     */
    private String arrisDesc;

    /**
     * 表达式版本状态(是否启用): 1 是 0 否'
     */
    private Byte arrisState;

    /**
     * 创建人
     */
    private Integer createId;

    /**
     * 创建时间
     */

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private String createTime;

    /**
     * 更新人
     */
    private Integer updateId;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JSONField(format="yyyy-MM-dd")
    private String updateTime;

    /**
     * 扩展字段
     */
    private String ext;


    private int pageNumber;

    private int pageSize;

    private String create_start;

    private String create_end;

    private List<Integer> idList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArrisName() {
        return arrisName;
    }

    public void setArrisName(String arrisName) {
        this.arrisName = arrisName;
    }

    public Integer getArrisVers() {
        return arrisVers;
    }

    public void setArrisVers(Integer arrisVers) {
        this.arrisVers = arrisVers;
    }

    public String getArrisDesc() {
        return arrisDesc;
    }

    public void setArrisDesc(String arrisDesc) {
        this.arrisDesc = arrisDesc;
    }

    public Byte getArrisState() {
        return arrisState;
    }

    public void setArrisState(Byte arrisState) {
        this.arrisState = arrisState;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getCreate_start() {
        return create_start;
    }

    public void setCreate_start(String create_start) {
        this.create_start = create_start;
    }

    public String getCreate_end() {
        return create_end;
    }

    public void setCreate_end(String create_end) {
        this.create_end = create_end;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    @Override
    public String toString() {
        return "ArrisInfo{" +
                "id=" + id +
                ", arrisName='" + arrisName + '\'' +
                ", arrisVers=" + arrisVers +
                ", arrisDesc='" + arrisDesc + '\'' +
                ", arrisState=" + arrisState +
                ", createId=" + createId +
                ", createTime='" + createTime + '\'' +
                ", updateId=" + updateId +
                ", updateTime='" + updateTime + '\'' +
                ", ext='" + ext + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", create_start='" + create_start + '\'' +
                ", create_end='" + create_end + '\'' +
                ", idList=" + idList +
                '}';
    }
}