/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("httpRequest")
@Scope("prototype")
public class CustomHttpRequest {
    
    public static final boolean GET_BODY = true;
    public static final boolean ONLY_HEADERS = false;
    
    private boolean requestStatus;
    public boolean getRequestStatus () {
        return this.requestStatus;
    }
    private String requestErrorMessage;
    public String getRequestErrorMessage () {
        return this.requestErrorMessage;
    }
    
    private HttpURLConnection conn;
    private StringBuilder header;
    private StringBuilder responseBody;
    private Map<String, String> responseHeaders; 
    private Map<String, String> requestHeaders;
    private Map<String, String> requestProperty;
    private Map<String, String> urlQueryProperties;
    private String url;
    private String requestMethod;
    private int connectionTimeout;
    private int readTimeout;
    private boolean getBodyStatus;
    
    public void setUrlQueryProperty (String key, String value) {
        this.urlQueryProperties.put(key, value);
    }
    
    public String getUrlQueryProperty (String key) {
        return this.urlQueryProperties.get(key);
    }
    
    public void clearUrlQueryProperty (String key) {
        this.urlQueryProperties.remove(key);
    }
    
    public void clearUrlQueryProperties () {
        this.urlQueryProperties.clear();
    }
    
    public int getUrlQueryPropertiesSize (){
        return this.urlQueryProperties.size();
    }
    
    public String getResponseBody () {
        return this.responseBody.toString();
    }
    
    public String getResponseHeader (String headerName) {
        if (! this.responseHeaderIsSet(headerName)) {
            return "";
        }
        return this.responseHeaders.get(headerName);
    }
    public boolean responseHeaderIsSet(String headerName) {
        return this.responseHeaders.containsKey(headerName);
    }
    public Map<String, String> getResponseHeaders () {
        return this.responseHeaders;
    }
    
    public String getRequestHeader (String headerName) {
        if (! this.requestHeaderIsSet(headerName)) {
            return "";
        }
        return this.requestHeaders.get(headerName);
    }
    public boolean requestHeaderIsSet(String headerName) {
        return this.requestHeaders.containsKey(headerName);
    }
    public Map<String, String> getRequestHeaders () {
        return this.requestHeaders;
    }
    public void fillRequestHeader (Map<String, String> requestHeaders) {
        this.requestHeaders = new HashMap<String, String>(requestHeaders);
    }
    public void setRequestHeader (String key, String value) {
        this.requestHeaders.put(key, value);
    }
    public void deleteRequestHeader (String key) {
        this.requestHeaders.remove(key);
    }
    public void clearRequestHeaders () {
        this.requestHeaders.clear();
    }
    
    public String getRequestProperty (String propertyName) {
        if (! this.requestPropertysSet(propertyName)) {
            return "";
        }
        return this.requestProperty.get(propertyName);
    }
    public boolean requestPropertysSet(String propertyName) {
        return this.requestProperty.containsKey(propertyName);
    }
    public Map<String, String> getRequestProperty () {
        return this.requestProperty;
    }
    public void fillRequestProperty (Map<String, String> requestProperty) {
        this.requestProperty = new HashMap<String, String>(requestProperty);
    }
    public void setRequestProperty (String key, String value) {
        this.requestProperty.put(key, value);
    }
    public void deleteRequestProperty (String key) {
        this.requestProperty.remove(key);
    }
    public void clearRequestProperty () {
        this.requestProperty.clear();
    }
    
    public String getUrl () {
        return this.url;
    }
    public void setUrl (String url) {
        this.url = url;
    }
    public String getRequestMethod () {
        return this.requestMethod;
    }
    public void setRequestMethod (String requestMethod) {
        this.requestMethod = requestMethod;
    }
    
    public int getConnectionTimeout () {
        return this.connectionTimeout;
    }
    public void setConnectionTimeout (int timeout) {
        this.connectionTimeout = timeout; 
    }
    
    public int getReadTimeout () {
        return this.readTimeout;
    }
    public void setReadTimeout (int timeout) {
        this.readTimeout = timeout;
    }
    
    private CustomHttpRequest() {
        this.initialize();
    }
   
    @PostConstruct
    private void initialize () {
        this.header = new StringBuilder();
        this.responseBody = new StringBuilder();
        this.responseHeaders = new HashMap<String, String>();
        this.requestHeaders = new HashMap<String, String>();
        this.requestProperty = new HashMap<String, String>();
        this.urlQueryProperties = new HashMap<String, String>();
        this.url = "";
        this.requestMethod = "GET";
        this.connectionTimeout = 100000;
        this.readTimeout = 100000;
        this.requestStatus = false;
        this.requestErrorMessage = "";
    }
    
    private void makeRequest () throws MalformedURLException, IOException {
        StringBuilder queryProperties = new StringBuilder();
        if (this.urlQueryProperties.size() > 0) {
            int size = this.urlQueryProperties.size();
            int iterate = 0;
            for (String key : this.urlQueryProperties.keySet()) {
                 iterate++;
                 queryProperties.append(key);
                 queryProperties.append("=");
                 queryProperties.append(this.urlQueryProperties.get(key));
                 if (iterate < size) {
                     queryProperties.append("&");
                 }
            }
        }
        URL urlObj = new URL(this.url + "?" + queryProperties.toString());
        this.conn = (HttpURLConnection) urlObj.openConnection();
        this.conn.setRequestMethod(this.requestMethod);
        this.conn.setConnectTimeout(this.connectionTimeout);
        this.conn.setReadTimeout(this.readTimeout);
        if (! this.requestHeaders.isEmpty()) {
            for (String key : this.requestHeaders.keySet()) {
                 this.conn.setRequestProperty(key, this.requestHeaders.get(key));
            }
        }
        this.responseHeaders.clear();
        if (this.responseBody.length() > 0) {this.responseBody.delete(0, this.responseBody.length());}
        this.conn.connect();
        this.responseHeaders.put("status", Integer.toString(this.conn.getResponseCode()));
        StringBuilder tmpStrBuidler = new StringBuilder();
        for (Entry<String, List<String>> row : this.conn.getHeaderFields().entrySet() ){
             if (row.getKey() == null) {continue;}
             if (tmpStrBuidler.length() > 0) tmpStrBuidler.delete(0, tmpStrBuidler.length());
             for (String headerValue : row.getValue()) {
                  tmpStrBuidler.append(headerValue);
             }
             this.responseHeaders.put(row.getKey(), tmpStrBuidler.toString());
        }
        if (this.getBodyStatus == true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                   this.responseBody.append(line);
            }
        }
        
    }
    
    private void request () {
        this.requestStatus = true;
        this.requestErrorMessage = "";
        try {
            this.makeRequest();
        } catch (MalformedURLException malf) {
            this.requestStatus = false;
            this.requestErrorMessage = malf.getMessage();
        } catch (IOException ioexc) {
            this.requestStatus = false;
            this.requestErrorMessage = ioexc.getMessage();
        } catch (Exception exc) {
            this.requestStatus = false;
            this.requestErrorMessage = exc.getMessage();
        }
    }
    
    public void runRequest () {
       this.getBodyStatus = CustomHttpRequest.GET_BODY;
       this.request();
    }
    
    public void runRequest (boolean getBody) {
       this.getBodyStatus = getBody;
       this.request();
    }
  
  
    
    
    
}