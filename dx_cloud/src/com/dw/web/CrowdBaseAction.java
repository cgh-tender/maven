/*
 * Copyright (c) 2005 Authentisoft, LLC. All Rights Reserved.
 */
package com.dw.web;

import com.atlassian.crowd.exception.InvalidAuthenticationException;
import com.atlassian.crowd.integration.http.CrowdHttpAuthenticator;
import com.atlassian.crowd.model.user.User;
import com.atlassian.crowd.service.client.CrowdClient;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CrowdBaseAction  
{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected int tab = 1;
    protected User remoteUser;
    protected Boolean authenticated = null;

    protected CrowdClient crowdClient;
    protected CrowdHttpAuthenticator crowdHttpAuthenticator;
    
     private  HttpServletRequest request;
     
     private  HttpServletResponse response;

    
    public String doDefault() {
        return "success";
    }

    public String getDisplayName() throws InvalidAuthenticationException {
        if (!isAuthenticated())
            return null;

        String displayName = "";

        User user = getRemoteUser();
        if (user != null)
        {
            displayName = user.getDisplayName();
        }

        return displayName;
    }

    public User getRemoteUser() throws InvalidAuthenticationException
    {
        if (!isAuthenticated())
            return null;

        if (remoteUser == null)
        {

            try
            {
                 remoteUser = crowdHttpAuthenticator.getUser(request);
            }
            catch (Exception e)
            {
                logger.info(e.getMessage(), e);

                throw new InvalidAuthenticationException("", e);
            }
        }

        return remoteUser;
    }

   
    @SuppressWarnings("deprecation")
	public boolean isAuthenticated()
    {
        if (authenticated == null) {
            try {
                authenticated = crowdHttpAuthenticator.isAuthenticated(request, response);
            }
            catch (Exception e) {
                logger.info(e.getMessage(), e);
                authenticated = Boolean.FALSE;
            }
        }
        return authenticated;
    }

    protected HttpSession getSession () {
        return request.getSession();
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }

    public CrowdClient getCrowdClient()
    {
        return crowdClient;
    }

    public void setCrowdClient(CrowdClient crowdClient)
    {
        this.crowdClient = crowdClient;
    }

    public CrowdHttpAuthenticator getCrowdHttpAuthenticator()
    {
        return crowdHttpAuthenticator;
    }

    public void setCrowdHttpAuthenticator(CrowdHttpAuthenticator httpAuthenticator)
    {
        this.crowdHttpAuthenticator = httpAuthenticator;
    }
}
