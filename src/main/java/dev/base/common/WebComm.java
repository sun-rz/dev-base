package dev.base.common;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WebComm {

    /**
     * 获取requestBody的JSON数据
     *
     * @param request
     * @return
     */
    public static String getRequestBodyString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream,
                    "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static JSONObject getRequestBodyJSON(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream,
                    "UTF-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            if (isBlank(sb.toString())) {
                return new JSONObject();
            } else {
                return JSONObject.fromObject(sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONObject();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isBlank(Object obj) {
        if (null == obj || "".equals(obj) || "null".equals(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 获取JSON格式的返回数据
     *
     * @param msg
     * @param success
     * @return
     */
    public static String getReturnJSON(String msg, boolean success) {
        JSONObject obj = new JSONObject();
        obj.put("msg", msg);
        obj.put("success", success);
        return obj.toString();
    }

    /**
     * 验证请求的合法性，防止跨域攻击
     *
     * @param request
     * @return
     */
    public static int validateReferURL(HttpServletRequest request, String referer) {
        try {
            //获取请求来源的服务器主机host名
            String referURL = request.getHeader("referer");
            if (isBlank(referURL)) {
                //非法操作
                return 0;
            }

            if (!isBlank(referer)) {//只允许在referer页面提交
                if (!referURL.contains(referer)) {
                    return 1;
                }
            }
            URL urlOne = new URL(referURL);
            String refererHostName = urlOne.getHost();

            //获取当前页面的 路径，并通过路径获得本服务器host名
            String requestURL = request.getRequestURL().toString();
            URL urlTwo = new URL(requestURL);
            String thisHost = urlTwo.getHost();
            //判断是否为从外部登录
            if (!refererHostName.equals(thisHost)) {
                //转向提示页面，显示不能从外部登录或提交表单
                return 1;
            }
            return 2;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获得MD5加密字符
     *
     * @param src
     * @return
     */
    public static String getMD5Encode(String src) {

        if (isBlank(src)) {
            return "";
        }
        // 需要加密的字符串
        try {
            // 加密对象，指定加密方式
            MessageDigest md5 = MessageDigest.getInstance("md5");
            // 准备要加密的数据
            byte[] b = src.getBytes();
            // 加密
            byte[] digest = md5.digest(b);
            // 十六进制的字符
            char[] chars = new char[]{'0', '1', '2', '3', '4', '5',
                    '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
            StringBuffer sb = new StringBuffer();
            // 处理成十六进制的字符串(通常)
            for (byte bb : digest) {
                sb.append(chars[(bb >> 4) & 15]);
                sb.append(chars[bb & 15]);
            }
            // 打印加密后的字符串
            //System.out.println(sb);
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
