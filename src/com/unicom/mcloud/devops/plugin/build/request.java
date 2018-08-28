package com.unicom.mcloud.devops.plugin.build;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class request {
	
	public static Map<String, Object> request(String url, String parameters) {  
        Map<String, Object> result = new HashMap<String, Object>();  
        String errorStr = "";  
        String status = "";  
        String response = "";  
        OutputStreamWriter out = null;  
        BufferedReader in = null; 
                
        try {  
            URL realUrl = new URL(url);  
            // �򿪺�URL֮�������  
            URLConnection conn = realUrl.openConnection();  
            HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;  
            // ������������  
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");  
            
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setInstanceFollowRedirects(true);
            httpUrlConnection.setRequestMethod("POST");
            
            // ����POST�������������������  
            httpUrlConnection.setDoOutput(true);  
            httpUrlConnection.setDoInput(true); 
            
            
            
            httpUrlConnection.connect();
            
            // ��ȡURLConnection�����Ӧ�������  
            out = new OutputStreamWriter(httpUrlConnection.getOutputStream(), "UTF-8");  
            // �����������  
            out.append(parameters);  
            // flush������Ļ���  
            out.flush();  
  
            // ����BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), "UTF-8"));  
            String line;  
            while ((line = in.readLine()) != null) {  
                response += line;  
            }  
            status = new Integer(httpUrlConnection.getResponseCode()).toString();  
        } catch (Exception e) {  
            System.out.println("���� POST ��������쳣��" + e);  
            errorStr = e.getMessage();  
        }  
        // ʹ��finally�����ر��������������  
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
