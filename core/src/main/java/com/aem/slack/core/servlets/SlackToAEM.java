package com.aem.slack.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import javax.jcr.Session; 
import javax.jcr.Node;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletResponse;

import java.rmi.ServerException;

import org.apache.sling.jcr.api.SlingRepository;

import java.io.*;


@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "= Create ticket from slack bot",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths="+ "/bin/ticketingsystem/createticket"
})
public class SlackToAEM extends SlingAllMethodsServlet {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Reference
	private ResourceResolverFactory resolverFactory;
	
	private Session session;
	
	
	@Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
    	
		logger.debug("Fetching Slack data in POST");
    	
		logger.debug("payload :: "+ request.getAttributeNames().nextElement().toString());
		Enumeration itr = request.getParameterNames();
		while (itr.hasMoreElements()) {
			String attr = itr.nextElement().toString();
			logger.debug("attributes :: "+ attr);
		}
		
		String createdByName = request.getParameter("user_name");
		String createdById = request.getParameter("user_id");
		String command = request.getParameter("command");
		String userQuery = request.getParameter("text");
		String channelName = request.getParameter("channel_name");
		String channelId = request.getParameter("channel_name");
		String response_url = request.getParameter("response_url");
		String triggerId = request.getParameter("trigger_id");
		
		logger.debug("query recieved ::>> "+ userQuery);
		
		String nodePath = "/content/tickets";
		String identifierOpen = "/open";
		String identifierClosed = "/closed";
  	    
		Map<String, Object> param = new HashMap<>();
		//logger.debug("got param : "+resolverFactory.SUBSERVICE+" end");
	    param.put(ResourceResolverFactory.SUBSERVICE, "ACLReader");
	    //logger.debug("added subsirvice to param"+resolverFactory+" end");
	    try{
	    	ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param);
	    	session = resourceResolver.adaptTo(Session.class);
	    	
	    	Node ticketNodeParent = session.getNode(nodePath+identifierOpen);
	    	//String ticketCount = ticketNodeParent.getProperty("ticketcount").toString();
	    	//logger.debug("ticketCount  :: "+ticketCount);
	    	//ticketCount=ticketCount++;
		    Node ticketNode = ticketNodeParent.addNode(triggerId);
		    //Node day = ticketNode.addNode();
		    ticketNode.setProperty("query", userQuery);
		    ticketNode.setProperty("raisedBy", createdByName);
		    ticketNode.setProperty("raisedById", createdById);
		    ticketNode.setProperty("assignee", "None");
		    ticketNode.setProperty("status", "Created");

		    Node commentNode = ticketNode.addNode("comments");
		    commentNode.setProperty("commentCount", "0");

		    // Save the session changes and log out
		    session.save();
		    session.logout();
	    }catch(Exception e){
			logger.debug("expection thrown  :: "+e);
		}
		
		response.getWriter().println("Recieved query :: "+userQuery+" by :: "+ createdByName);
		response.setContentType("text/plain;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
	
  	    response.setStatus(200);
  
  	    
  	    

  	    
    	
    	return;
    	
    	
    }
    
    

	



	
    

	

	


	@Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
		
	}
     

	

     
    
}