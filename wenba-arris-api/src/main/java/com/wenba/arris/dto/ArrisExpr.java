package com.wenba.arris.dto;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * arris_expr
 * @author 
 */
public class ArrisExpr implements Serializable {


    private static final long serialVersionUID = 1L;
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 表达式id
     */
    private Integer infoId;

    /**
     * 表达式详情
     */
    private String arrisDetail;

    /**
     * 表达式详情版本
     */
    private Integer exprVers;

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

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }

    public String getArrisDetail() {
        return arrisDetail;
    }

    public void setArrisDetail(String arrisDetail) {
        this.arrisDetail = arrisDetail;
    }

    public Integer getExprVers() {
        return exprVers;
    }

    public void setExprVers(Integer exprVers) {
        this.exprVers = exprVers;
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
        return "ArrisExpr{" +
                "id=" + id +
                ", infoId=" + infoId +
                ", arrisDetail='" + arrisDetail + '\'' +
                ", exprVers=" + exprVers +
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