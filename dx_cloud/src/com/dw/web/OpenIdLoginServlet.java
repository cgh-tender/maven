package com.dw.web;

import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.message.AuthRequest;

public class OpenIdLoginServlet {

	String Openid = "gsdfgsdfgdfsg";
	public void MethodTest(){
		if(null != Openid ){
 			String returnUrl = RegistrationService.getReturnToUrl();
  			DiscoveryInformation discoveryInformation = RegistrationService
					.performDiscoveryOnUserSuppliedIdentifier(Openid);
 			AuthRequest authRequest = RegistrationService.createOpenIdAuthRequest(discoveryInformation, returnUrl);
			
 			
		}
	}
	
}
