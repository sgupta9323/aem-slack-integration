package com.aem.slack.core.servlets;

import com.aem.slack.core.service.*;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session; 

import javax.servlet.Servlet;

import java.rmi.ServerException;


import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;


import java.io.*;

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "= Send update to Slack",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths="+ "/bin/ticketingsystem/addComment"
})
public class AddComment extends SlingAllMethodsServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	private Session session;
	
	@Reference
	ProcessComment processComment;
	
	@SlingObject
	SlingHttpServletRequest request;
	
	@Reference
	private SlingRepository repository;
    
    public void bindRepository(SlingRepository repository) {
           this.repository = repository; 
           }
	
    
    //@SuppressWarnings("deprecation")
	@Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
    	
		logger.debug("Adding comment and sending to Slack");
		
		String reqArgs = request.getParameter("sFilterData");
		String[] reqParams = reqArgs.split("__");
		String ticketNo = reqParams[0];
		String ticketNoTrimmed = ticketNo.substring(1, ticketNo.length()-1);
		String commentText = reqParams[1];
		logger.debug("ticketNo :: "+ ticketNoTrimmed);
		logger.debug("commentText :: "+ commentText);
		
		processComment.addCommentRepo(commentText, ticketNoTrimmed);
		processComment.addCommentSlack(commentText, ticketNoTrimmed);
		
		logger.debug("comment added successfully ");

		
  	  	
  	  	    	
    }
    
  


	@Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {}
     

	

     
    
}