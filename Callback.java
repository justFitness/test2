package com.spsy.order.util;

import com.spsy.common.utils.JSONUtil;
import com.spsy.order.util.kuaidi.TaskResponse;
//import pojo.NoticeRequest;
//import pojo.Result;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Author: liYinHai
 * @Date: 2019/05/07 14:56
 * @Description: TODO
 */
public class Callback extends HttpServlet implements Serializable {

    private static final long serialVersionUID = -174870675181336930L;

    public Callback() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        TaskResponse resp = new TaskResponse();
        resp.setResult(false);
        resp.setReturnCode("500");
        resp.setMessage("保存失败");
        try {
            String param = request.getParameter("param");
            //NoticeRequest nReq = JSONUtil.parseJSON(param,
            //        NoticeRequest.class);
            //
            //Result result = nReq.getLastResult();
            // 处理快递结果

            resp.setResult(true);
            resp.setReturnCode("200");
            //这里必须返回，否则认为失败，过30分钟又会重复推送。
            response.getWriter().print(JSONUtil.parseJSON(resp));
        } catch (Exception e) {
            resp.setMessage("保存失败" + e.getMessage());
            //保存失败，服务端等30分钟会重复推送。
            response.getWriter().print(JSONUtil.parseJSON(resp));
        }
    }

}
