package com.demo.java.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.java.utils.Constants;

public class HttpUtils {

    static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    final static int CONNECT_TIME_OUT = 1000 * 2;
    final static int READ_TIME_OUT = 1000 * 2;
    final static int GET_CACHE = 1024 * 4;

    public static HttpClient getClient() {
        HttpClient client = new HttpClient();// 获取HttpClient对象
        try {
            HttpConnectionManagerParams mgrParams = client.getHttpConnectionManager().getParams();
            mgrParams.setConnectionTimeout(CONNECT_TIME_OUT);
            mgrParams.setSoTimeout(READ_TIME_OUT);
            client.getHttpConnectionManager().setParams(mgrParams);
        } catch (Exception e) {
            logger.error("getClient Exception : {}", e.getMessage(), e);
        }
        return client;
    }

    /**
     * Get方法提交
     * 
     * @param url
     * @param paramMap
     */
    public static String doGet(String url, Map<String, String> paramMap) {
        String res = "";
        HttpClient client = null;
        GetMethod method = null;
        ByteArrayOutputStream outstream = null;
        InputStream instream = null;
        int statusCode = 0;
        try {
            StringBuffer urlSb = new StringBuffer(url);
            if ((paramMap != null) && !paramMap.isEmpty()) {
                urlSb.append(url.indexOf("?") > 0 ? "&" : "?");
                for (String key : paramMap.keySet()) {
                    String value = paramMap.get(key);
                    urlSb.append(key).append("=").append(URLEncoder.encode(value, Constants.ENCODING)).append("&");
                }
            }
            client = getClient();
            logger.info("doGet url : {}", urlSb.toString());
            method = new GetMethod(urlSb.toString());
            // method set retry times
            HttpMethodRetryHandler myRetryHandler = new DefaultHttpMethodRetryHandler(3, false);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, myRetryHandler);
            statusCode = client.executeMethod(method);
            logger.info("doGet HttpStatus Code : {}", statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                instream = method.getResponseBodyAsStream();
                if (instream != null) {
                    outstream = new ByteArrayOutputStream(GET_CACHE);
                    byte buffer[] = new byte[GET_CACHE];
                    int i;
                    while ((i = instream.read(buffer)) > 0) {
                        outstream.write(buffer, 0, i);
                    }
                    outstream.close();
                }
                res = new String(outstream.toByteArray());
            }
        } catch (HttpException e) {
            logger.error("doGet HttpException : {}", e.getMessage(), e);
        } catch (IOException e) {
            logger.error("doGet IOException : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("doGet Exception : {}", e.getMessage(), e);
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return res;
    }

    /**
     * Post方法提交
     * 
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, Map<String, String> params) {
        String res = "";
        HttpClient client = null;
        PostMethod postMethod = null;
        ByteArrayOutputStream outstream = null;
        InputStream instream = null;
        int statusCode = 0;
        try {
            client = getClient();
            postMethod = new PostMethod(url);
            HttpMethodRetryHandler myRetryHandler = new DefaultHttpMethodRetryHandler(3, false);
            // 设置Http Post数据
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            if ((params != null) && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    NameValuePair simcard = new NameValuePair(entry.getKey(), entry.getValue());
                    list.add(simcard);
                }
                postMethod.setRequestBody(list.toArray(new NameValuePair[list.size()]));
            }
            HttpMethodParams hmParams = postMethod.getParams();
            hmParams.setParameter(HttpMethodParams.RETRY_HANDLER, myRetryHandler);
            hmParams.setParameter(HttpMethodParams.CREDENTIAL_CHARSET, Constants.ENCODING);
            postMethod.setParams(hmParams);
            statusCode = client.executeMethod(postMethod);
            logger.info("doPost HttpStatus Code : {}", statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                instream = postMethod.getResponseBodyAsStream();
                if (instream != null) {
                    outstream = new ByteArrayOutputStream(4096);
                    byte buffer[] = new byte[4096];
                    int i;
                    while ((i = instream.read(buffer)) > 0) {
                        outstream.write(buffer, 0, i);
                    }
                    outstream.close();
                }
                res = new String(outstream.toByteArray());
            }
        } catch (HttpException e) {
            logger.error("doPost HttpException : {}", e.getMessage(), e);
        } catch (IOException e) {
            logger.error("doPost IOException : {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("doPost Exception : {}", e.getMessage(), e);
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }
            if (postMethod != null) {
                client.getHttpConnectionManager().closeIdleConnections(0);
            }
        }
        return res;
    }
}