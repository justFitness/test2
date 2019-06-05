package com.spsy.common.po;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Deng.Xiao
 * @Date: 2019/3/7
 * @Description: 构造两个时间 序列化
 */
@Data
public class BasePojo implements Serializable {

    private Date createTime;
    private Date updateTime;
}
