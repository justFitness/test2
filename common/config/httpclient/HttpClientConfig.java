package com.spsy.common.config.httpclient;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: Deng.Xiao.
 * @Date: 2019/4/18 10:06
 * @Description:
 */
@Configuration
@ImportResource(locations = {"classpath:application-httpclient.xml"})
public class HttpClientConfig {
}
