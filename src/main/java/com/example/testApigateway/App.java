package com.example.testApigateway;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.ProxyAuthenticationMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.AmazonApiGatewayClientBuilder;



public class App 
{
	BasicAWSCredentials awsCreds = new BasicAWSCredentials("ACCESKEY", "SECRETKEY");
   public void test() {
	   AmazonApiGateway gateway = AmazonApiGatewayClientBuilder.standard()
			   .withClientConfiguration(clientConfig("username","password"))
               .withCredentials(new AWSStaticCredentialsProvider(awsCreds)).withRegion(Regions.EU_WEST_3).build();


   }
   
   ClientConfiguration clientConfig(String username,String password) {
	    ClientConfiguration conf = new ClientConfiguration();
	    List<ProxyAuthenticationMethod> proxyAuthentication = new ArrayList<>(1);
	    proxyAuthentication.add(ProxyAuthenticationMethod.BASIC);
	    conf.setProxyAuthenticationMethods(proxyAuthentication);
	    conf.setProxyHost("HOST");
	    conf.setProxyPort(332);
	    conf.setProxyUsername(username);
	    conf.setProxyPassword(password);
	    return conf;
	}
}