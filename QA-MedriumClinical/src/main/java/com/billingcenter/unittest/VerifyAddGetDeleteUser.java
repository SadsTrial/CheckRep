package com.billingcenter.unittest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.sharedlibrary.ApacheHttpClient;
import com.sharedlibrary.ParseXML;

public class VerifyAddGetDeleteUser {

	@Test(description = "Test script to verify Add/Get/Delete user")
	@Parameters( {"billingCenterURL"})
	public void verifyAddGetDeleteUser(String billingCenterURL){

		try {
			ApacheHttpClient objClientConnector = new ApacheHttpClient();
			ParseXML objParser = new ParseXML("billingcenter");
			String errorMessage ="";
			String responseCode = "";
			Hashtable<String, String> reqUserDetails = objParser.fetchData("addValidUser.xml","user");
			String response =  objClientConnector.postXMLContent(objParser.getXMLInputStream("getUser.xml"), billingCenterURL);
			InputStream is = new ByteArrayInputStream(response.trim().split("@@")[1].getBytes());
			Hashtable<String, String> resUserDetails = objParser.fetchData(is,"Return");

			// Verify User already Exists
			if(resUserDetails.containsKey("firstname")){
				// if user Not Exists add the User
				if(!(resUserDetails.get("firstname").equalsIgnoreCase(reqUserDetails.get("firstname")))){
					response = null;
					response =  objClientConnector.postXMLContent(objParser.getXMLInputStream("addValidUser.xml"), billingCenterURL);
					responseCode = response.split("@@")[0];
					Assert.assertTrue(responseCode.trim().equalsIgnoreCase("200"),"Add User Response Code Error Expected response: 200; Actual :" + responseCode);
					Assert.assertFalse(response.trim().split("@@")[1].toLowerCase(new java.util.Locale("en", "US")).contains("exception"),"In valid Response or exception in add user call" + response.trim().split("@@")[1]);
					is = new ByteArrayInputStream(response.trim().split("@@")[1].getBytes());
					resUserDetails = objParser.fetchData(is,"Return");
					errorMessage = verifyData(reqUserDetails, resUserDetails);
					Assert.assertTrue(errorMessage.trim().equals(""),"Add User Call Failed; More Details " + errorMessage);
				}
			}else{
				response = null;
				response =  objClientConnector.postXMLContent(objParser.getXMLInputStream("addValidUser.xml"), billingCenterURL);
				responseCode = response.split("@@")[0];
				Assert.assertTrue(responseCode.trim().equalsIgnoreCase("200"),"Response Code Error Expected response: 200; Actual :" + responseCode);
				Assert.assertFalse(response.trim().split("@@")[1].toLowerCase(new java.util.Locale("en", "US")).contains("exception"),"In valid Response or exception in add user call " + response.trim().split("@@")[1]);
				is = new ByteArrayInputStream(response.trim().split("@@")[1].getBytes());
				resUserDetails = objParser.fetchData(is,"Return");
				errorMessage = verifyData(reqUserDetails, resUserDetails);
				Assert.assertTrue(errorMessage.trim().equals(""),"Add User Call Failed; More Details " + errorMessage);
			}

			// Get User Details
			response = null;
			response =  objClientConnector.postXMLContent(objParser.getXMLInputStream("getUser.xml"), billingCenterURL);
			responseCode = response.split("@@")[0];
			Assert.assertTrue(responseCode.trim().equalsIgnoreCase("200"),"Get User Call Response Code Error Expected response: 200; Actual :" + responseCode);
			Assert.assertFalse(response.trim().split("@@")[1].toLowerCase(new java.util.Locale("en", "US")).contains("exception"),"In valid Response or exception in get user call " + response.trim().split("@@")[1]);
			is = new ByteArrayInputStream(response.trim().split("@@")[1].getBytes());
			resUserDetails = objParser.fetchData(is,"Return");
			errorMessage = verifyData(reqUserDetails, resUserDetails);
			Assert.assertTrue(errorMessage.trim().equals(""),"Get User Call Failed; More Details " + errorMessage);

			//Delete User Details
			response = null;
			response =  objClientConnector.postXMLContent(objParser.getXMLInputStream("deleteUser.xml"), billingCenterURL);
			responseCode = response.split("@@")[0];
			Assert.assertTrue(responseCode.trim().equalsIgnoreCase("200"),"DeleteUser Call Response Code Error; Expected response: 200; Actual :" + responseCode);
			Assert.assertFalse(response.trim().split("@@")[1].toLowerCase(new java.util.Locale("en", "US")).contains("exception"),"In valid Response or Exception in delete call " + response.trim().split("@@")[1]);
			Assert.assertTrue(response.trim().split("@@")[1].toLowerCase(new java.util.Locale("en", "US")).contains("<response></response>"),"In valid Response in delete call " + response.trim().split("@@")[1]);

			//Verify the Deletion

			response =  objClientConnector.postXMLContent(objParser.getXMLInputStream("getUser.xml"), billingCenterURL);
			responseCode = response.split("@@")[0];
			Assert.assertTrue(responseCode.trim().equalsIgnoreCase("200"),"Get User Call Response Code After deltion Error Expected response: 200; Actual :" + responseCode);
			is = new ByteArrayInputStream(response.trim().split("@@")[1].getBytes());
			resUserDetails = objParser.fetchData(is,"Return");
			errorMessage = verifyData(reqUserDetails, resUserDetails);
			Assert.assertFalse(errorMessage.trim().equals(""),"The User Details are not deleted; Received response for Get user after deleted :  " +response.trim().split("@@")[1]);

		} catch (Exception e) {
			Assert.fail("Un expected Exception is occurred; More Details    : " + e.getMessage());
		}
	}

	public String verifyData(Hashtable<String, String> request,Hashtable<String, String> response){
		StringBuffer errorMessage = new StringBuffer();
		//String errorMessage = "";
		for(int i=0;i<request.size();i++){
			if(response.containsKey(request.keySet().toArray()[i].toString())){
				if(!response.get(request.keySet().toArray()[i].toString()).equalsIgnoreCase(request.get(request.keySet().toArray()[i].toString()))){
					errorMessage.append(request.keySet().toArray()[i].toString()).append(" validation failed; Expected : ").append(response.get(request.keySet().toArray()[i].toString())).append("Actual   :  ").append(request.get(request.keySet().toArray()[i].toString())).append("   \n");	
				}
			}else{
				errorMessage.append(" The value for ").append(request.keySet().toArray()[i].toString()).append(" is missed  \n");
			}
		}
		return errorMessage.toString();
	}

}
