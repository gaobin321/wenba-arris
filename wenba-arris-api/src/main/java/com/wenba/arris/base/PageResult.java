package com.wenba.arris.base;


import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @ Author
 * @ Date       ：Created in 5:43 PM 2018/10/24
 * @ Version    ：1.0
 * @ Modified By：
 * @ Description：基类，分页
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> extends BaseResult {
    // 当前页
    Integer page;
    // 每页记录数
    Integer size;
    // 总页数
    Integer pages;
    // 总记录数
    Long total;
    // 数据列表
    List<T> list;

    public void setByPageInfoNoList(PageInfo pageInfo) {
        setPage(pageInfo.getPageNum());
        setSize(pageInfo.getPageSize());
        setPages(pageInfo.getPages());
        setTotal(pageInfo.getTotal());
    }

    public void setByPageInfo(PageInfo pageInfo) {
        setByPageInfoNoList(pageInfo);
        setList(pageInfo.getList());
    }


}

