package com.bigrobotstudios.pinoccio;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hectorurtubia on 4/20/14.
 */
class RestClient {
    String urlString;
    JSONObject jsonData;

    public RestClient(String urlString){
        this.urlString = urlString;
    }

    public void setJsonData(JSONObject jsonData){
        this.jsonData = jsonData;
    }

    private static String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException{
        InputStream is = entity.getContent();
        StringBuffer sb = new StringBuffer();
        int n = 1;
        while(n > 0){
            byte[] b = new byte[4096];
            n = is.read(b);
            if(n > 0){
                sb.append(new String(b, 0, n));
            }
        }
        return sb.toString();
    }

    public String post(){
        HttpClient client = HttpClients.createDefault();
        HttpContext context = new BasicHttpContext();
        HttpPost post = new HttpPost(urlString);
        post.setHeader("Content-type","application/x-www-form-urlencoded");
        BasicHttpEntity entity = new BasicHttpEntity();
        if(jsonData != null) {
            entity.setContent(new ByteArrayInputStream(jsonData.toString().getBytes()));
            post.setEntity(entity);
        }
        try {
            HttpResponse resp = client.execute(post, context);
            HttpEntity respEntity = resp.getEntity();
            String response = getASCIIContentFromEntity(respEntity);
            return response;
        }catch (ClientProtocolException clientProtocolException){

        }catch (IOException ioException){
            String str = ioException.getMessage();
        }catch (JSONException jsonException){

        }
        return null;
    }

    public String get(){
        HttpClient client = HttpClients.createDefault();
        HttpContext context = new BasicHttpContext();
        HttpGet get = new HttpGet(urlString);
        BasicHttpEntity entity = new BasicHttpEntity();
        try {
            HttpResponse resp = client.execute(get, context);
            HttpEntity respEntity = resp.getEntity();
            String response = getASCIIContentFromEntity(respEntity);
            return response;
        }catch (ClientProtocolException clientProtocolException){

        }catch (IOException ioException){
            String str = ioException.getMessage();
        }catch (JSONException jsonException){

        }
        return null;
    }
}
