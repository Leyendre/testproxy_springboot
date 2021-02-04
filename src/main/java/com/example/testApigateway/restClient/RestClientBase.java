package com.example.testApigateway.restClient;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.ProxyAuthenticationStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class RestClientBase {
	  private String PROXY_HOST = "127.0.0.1";
	  private int PROXY_PORT = 8888;
	  private String PROXY_USER = "";
	  private String PROXY_PASSWORD = "";

	  private RestTemplate template;

	  public RestTemplate getTemplate() {
	    if (template == null) {
	      CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

	    	  credentialsProvider.setCredentials(
	    			  new AuthScope(PROXY_HOST, PROXY_PORT),
	    			  new UsernamePasswordCredentials(PROXY_USER, PROXY_PASSWORD));


	      HttpClientBuilder clientBuilder = HttpClientBuilder.create();

	      clientBuilder.useSystemProperties();
	      clientBuilder.setProxy(new HttpHost(PROXY_HOST, PROXY_PORT));
	      clientBuilder.setDefaultCredentialsProvider(credentialsProvider);
	      clientBuilder.setProxyAuthenticationStrategy(new ProxyAuthenticationStrategy());

	      CloseableHttpClient client = clientBuilder.build();

	      HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	      factory.setHttpClient(client);

	      template = new RestTemplate();
	      template.setRequestFactory(factory);
	    }
	    return template;
	  }
	  
	  public void setProxyHost(String host) {
		  this.PROXY_HOST = host;
	  }
	  
	  public void setProxyPort(int port) {
		  this.PROXY_PORT = port;
	  }
	  
	  public void setProxyUser(String user) {
		  this.PROXY_USER = user;
	  }
	  
	  public void setProxyPassword(String password) {
		  this.PROXY_PASSWORD = password;
	  }
	  
	  public void clear() {
		  template = null;
	  }
	}