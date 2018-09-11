package com.unicom.mcloud.devops.plugin.build;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.json.JSONObject;

public class Request {
	
	public static String TEMPCOOKIE = "";
	
	public static Map<String, Object> postRequest(String url, JSONObject parameters) {  
        Map<String, Object> result = new HashMap<String, Object>();  
        String errorStr = "";  
        String status = "";  
        String response = "";  
        OutputStreamWriter out = null;  
        BufferedReader in = null; 
                
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;  
            // 设置请求属性  
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");  
            
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setInstanceFollowRedirects(true);
            httpUrlConnection.setRequestMethod("POST");
            if(!"".equals(TEMPCOOKIE)) {
            	httpUrlConnection.setRequestProperty("Cookie", TEMPCOOKIE); 
            }
            
            // 发送POST请求必须设置如下两行  
            httpUrlConnection.setDoOutput(true);  
            httpUrlConnection.setDoInput(true); 
            
            
            
            httpUrlConnection.connect();
            
            // 获取URLConnection对象对应的输出流  
            out = new OutputStreamWriter(httpUrlConnection.getOutputStream(), "UTF-8");  
            //  发送请求参数  
            out.append(parameters.toString());  
            // 获取URLConnection对象对应的输出流  
            out.flush();  
            
            
  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8")); 
            
            if("".equals(TEMPCOOKIE)) {
            	TEMPCOOKIE = httpUrlConnection.getHeaderFields().get("Set-Cookie").get(0);
            }
            
            String line;  
            while ((line = in.readLine()) != null) {  
                response += line;  
            }  
            status = new Integer(httpUrlConnection.getResponseCode()).toString();  
        } catch (Exception e) {  
            JOptionPane.showMessageDialog(null, e);
            errorStr = e.getMessage();
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) { out.close();}  
                if (in != null) {in.close();}  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
        result.put("errorStr", errorStr);  
        result.put("response", response);  
        result.put("status", status);  
        return result;  
  
    }  
	
	public static Map<String, Object> getRequest(String url) {  
        Map<String, Object> result = new HashMap<String, Object>();  
        String errorStr = "";  
        String status = "";  
        String response = "";  
        OutputStreamWriter out = null;  
        BufferedReader in = null; 
                
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;  
            // 设置请求属性  
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");  
            
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setInstanceFollowRedirects(true);
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("Cookie", TEMPCOOKIE);
            
            httpUrlConnection.connect();
            
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                response += line;  
            }  
            status = new Integer(httpUrlConnection.getResponseCode()).toString();  
        } catch (Exception e) {  
            JOptionPane.showMessageDialog(null, e);
            errorStr = e.getMessage();
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) { out.close();}  
                if (in != null) {in.close();}  
            } catch (Exception ex) {  
                ex.printStackTrace();  
            }  
        }  
        result.put("errorStr", errorStr);  
        result.put("response", response);  
        result.put("status", status);  
        return result;  
  
    }  

}
