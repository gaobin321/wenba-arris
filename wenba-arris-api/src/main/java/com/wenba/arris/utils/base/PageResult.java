package com.wenba.arris.utils.base;

import java.util.List;

public class PageResult<T> extends BaseResult {
    // 分页
    Integer page;
    // 每页记录数
    Integer size;
    // 总记录数量
    Long total;
    // 列表
    List<T> data;

    public PageResult() {
        super();
    }

    public PageResult(List<T> data) {
        this.data = data;
    }

    public PageResult(List<T> data, Integer page, Integer size, Long total) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.data = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
