package com.spsy.common.utils;

import com.spsy.common.vo.PageVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Deng.Xiao
 * @Date: 2019/3/6
 * @Description: 分页工具
 */
@Data
public class PageBean<T> extends PageVo {

    private List<T> list;

    private Integer total;

    public PageBean() {
        super();
    }

    /**
     * 用户web端分页
     * @param list
     * @param total 总页数
     * @param pageNum
     * @param pageSize
     */
    public PageBean(List<T> list, Integer total, Integer pageNum, Integer pageSize) {
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /***
     * 用于app端分页
     * @param list
     * @param pageNum
     * @param pageSize
     */
    public PageBean(List<T> list, Integer pageNum, Integer pageSize) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }


}
