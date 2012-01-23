package com.sharedlibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;

public class ApacheHttpClient {
	public String postXMLContent(String fileName,String strURL) throws Exception{
		String response = "";
		PostMethod objPost = new PostMethod(strURL);
			File dir1 = new File (".");
			String strBasePath=dir1.getCanonicalPath();
			String file=strBasePath+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"clinical"+File.separator+"XMLRepository"+
			File.separator+ fileName;
			File input = new File(file);
			objPost.setRequestEntity(new InputStreamRequestEntity(new FileInputStream(input), input.length()));
			objPost.setRequestHeader("Content-type", "text/xml; charset=utf-8");
			HttpClient httpclient = new HttpClient();
			int result = httpclient.executeMethod(objPost);
			response = result+"@@"+ objPost.getResponseBodyAsString();
			objPost.releaseConnection();
			return response;
		
		
		
		
	}

	public String postXMLContent(InputStream input,String strURL) throws Exception{
		String response = "";
		PostMethod objPost = new PostMethod(strURL);
			objPost.setRequestEntity(new InputStreamRequestEntity(input, input.available()));
			objPost.setRequestHeader("Content-type", "text/xml; charset=utf-8");
			HttpClient httpclient = new HttpClient();			
			int result = httpclient.executeMethod(objPost);
			response = result+"@@"+ objPost.getResponseBodyAsString();
			objPost.releaseConnection();
		
		return response;
	}
	}