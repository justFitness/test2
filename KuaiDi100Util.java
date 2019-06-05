package com.spsy.order.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spsy.common.utils.JSONUtil;
import com.spsy.order.util.kuaidi.TaskRequest;
import com.spsy.order.util.kuaidi.TaskResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: liYinHai
 * @Date: 2019/05/07 10:40
 * @Description: 快递查询工具 饿汉单例模式
 */
@Slf4j
public class KuaiDi100Util {

    private static final KuaiDi100Util instance = new KuaiDi100Util();


    public KuaiDi100Util() {
    }

    public static KuaiDi100Util getInstance() {
        return instance;
    }

    /**
     * 免费版调用方式
     *
     * @param key
     * @param com
     * @param nu
     * @return
     */
    public String getKuaiDiInfoFree(String key, String com, String nu){
        String content = "";
        try {
            URL url = new URL("http://www.kuaidi100.com/api?id=" + key + "&com=" + com + "&nu=" + nu + "&valicode=" + 0);
            URLConnection con = url.openConnection();
            //是否允许与用户交互。默认为false，即不允许交互。该值只能在URLConnection连接前设置。
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
// String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, "UTF-8");
                    content += newContent;
                }
            }
            urlStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("快递查询错误");
        }
        return content;
    }


    /**
     * 企业版调用方式（实时查询,订阅查询需要将数据保存到数据库）
     *
     * @param param customer 快递100给客户的编号
     *              key      授权密钥
     * @return
     */
    public List<Map<Object, Object>> getKuaiDiInfo4Firm(String param) {
        List<Map<Object, Object>> orderList = new ArrayList<Map<Object, Object>>();
        String customer = "";
        String key = "";
        String sign = MD5.encode(param + key + customer);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("param", param);
        params.put("sign", sign);
        params.put("customer", customer);
        String resp;
        try {
            resp = new HttpRequest().
                    postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            // 格式化数据
            JsonObject reslut = JSONUtil.parseJSON(resp,JsonObject.class);
            JsonArray kuaidiList = (JsonArray) reslut.get("data");
            if (kuaidiList != null && kuaidiList.size() > 0) {
                for (int i = 0; i < kuaidiList.size(); i++) {
                    JsonObject jobj = JSONUtil.parseJSON(kuaidiList.get(i)+"",JsonObject.class);
                    Map<Object, Object> map = new HashMap<Object, Object>();
                    map.put("time", jobj.get("time"));
                    map.put("context", jobj.get("context"));
                    orderList.add(map);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.error("快递查询错误");
        }
        return orderList;
    }


    //测试
    public static void main(String[] args) {
        String emsCode = "shentong";
        String emsCompCode = "3311699385987";
        String param = "{com:" +emsCode+ ",num:"+emsCompCode+"}";
        List<Map<Object, Object>> result = KuaiDi100Util.getInstance().getKuaiDiInfo4Firm(param);
        System.out.println(result.toString());
    }

    private static final String pollAddress = "https://poll.kuaidi100.com/poll";

    /* @Author: liyinhai
     * @Description: 订阅模式
     * @Date:  15:54 2019/5/7 0007
     **/
    public String getPost(){

        TaskRequest req = new TaskRequest();
        req.setCompany("yuantong");
        req.setFrom("上海浦东新区");
        req.setTo("广东深圳南山区");
        req.setNumber("12345678");
        req.getParameters().put("callbackurl", "http://www.yourdmain.com/kuaidi");
        req.setKey("testkuaidi1031");

        HashMap<String, String> p = new HashMap<String, String>();
        p.put("schema", "json");
        p.put("param", JSONUtil.parseJSON(req));
        try {
            String ret = HttpRequest.postData("http://www.kuaidi100.com/poll", p, "UTF-8");
            TaskResponse resp = JSONUtil.parseJSON(ret, TaskResponse.class);
            if(resp.isResult()==true){
                return "订阅成功";
            }else{
                return  "订阅失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "error";
    }

}




