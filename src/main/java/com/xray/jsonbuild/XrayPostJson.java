package com.xray.jsonbuild;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

import com.xray.common.FileDetails;
public class XrayPostJson {
	
	   public static void sendPostRequest(String payload) throws Exception {

		/*
		 * CredentialsProvider provider = new BasicCredentialsProvider();
		 * UsernamePasswordCredentials credentials = new
		 * UsernamePasswordCredentials("vinay.bhoj@maersk.com", "changeme10");
		 * provider.setCredentials(AuthScope.ANY, credentials);
		 * 
		 * StringEntity entity = new StringEntity(payload,
		 * ContentType.APPLICATION_JSON);
		 * 
		 * HttpClient httpClient = HttpClientBuilder.create()
		 * .setDefaultCredentialsProvider(provider) .build(); String baseUrl =
		 * FileDetails.getPropertyValueByKey("baseUrl"); String createPath =
		 * FileDetails.getPropertyValueByKey("createpath"); HttpPost request = new
		 * HttpPost(baseUrl+createPath);
		 * 
		 * request.setEntity(entity);
		 * 
		 * HttpResponse response = httpClient.execute(request);
		 * System.out.println(response.getStatusLine().getStatusCode());
		 */
		   

		   HttpHost proxy = new HttpHost("10.112.254.132", 8887);
		   DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		   
		   CredentialsProvider provider = new BasicCredentialsProvider();
		   UsernamePasswordCredentials credentials
		    = new UsernamePasswordCredentials("vinay.bhoj@maersk.com", "changeme10");
		   provider.setCredentials(AuthScope.ANY, credentials);
		   
        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClients.custom()
        		  .setRoutePlanner(routePlanner)
        		  .setDefaultCredentialsProvider(provider)
        		  .build();

        String baseUrl = FileDetails.getPropertyValueByKey("baseUrl");
        String createPath = FileDetails.getPropertyValueByKey("createpath");
        HttpPost request = new HttpPost(baseUrl+createPath);
        						
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getStatusLine().getStatusCode());
    }
}