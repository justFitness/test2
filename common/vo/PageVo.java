package com.spsy.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Deng.Xiao.
 * @Date: 2019/4/6 14:21
 * @Description:
 */
@Data
public class PageVo implements Serializable {

    protected Integer pageNum = 1;

    protected Integer pageSize = 10;
}
