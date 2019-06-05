package com.spsy.common.utils;

import com.common.mapper.SysMapper;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/04/25 16:04
 * @Description: 分页工具类
 */
public class PageUtil<T> {

    /**
     *  分页方法
     * @param sysMapper 某个类的mapper接口
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param <T>
     * @return 封装好的pagebean
     */
    public static<T> PageBean<T> page(SysMapper<T> sysMapper ,Integer pageNum, Integer pageSize){
        Integer offset = (pageNum - 1) * pageSize;
        Integer total = sysMapper.findCount();// 总数
        List<T> products = sysMapper.selectPage(offset, pageSize);
        total = (total % pageSize) == 0 ? total / pageSize : (total / pageSize) + 1;// 总页数
        PageBean<T> pageBean = new PageBean<>(products, total, pageNum, pageSize);
        return pageBean;
    }
}
