package com.aem.slack.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.PropertyType;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.QueryResult;
import javax.net.ssl.HttpsURLConnection;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import org.apache.jackrabbit.commons.JcrUtils;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.rmi.ServerException;

import com.aem.slack.core.service.ProcessComment;
import com.day.cq.dam.api.Asset;

import org.apache.sling.commons.osgi.OsgiUtil;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import org.osgi.service.component.ComponentContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "= Close the ticket and update to Slack",
        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
        "sling.servlet.paths="+ "/bin/ticketingsystem/closeTicket"
})
public class CloseTicket extends SlingAllMethodsServlet {
	
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
    	
		logger.debug("Closing the ticket and sending to Slack");
		
		String reqArgs = request.getParameter("sFilterData");
		String[] reqParams = reqArgs.split("__");
		String ticketNo = reqParams[0];
		String ticketNoTrimmed = ticketNo.substring(1, ticketNo.length()-1);
		String commentText = reqParams[1];
		commentText = "Ticket Closed with comments : "+commentText;
		logger.debug("ticketNo :: "+ ticketNoTrimmed);
		logger.debug("commentText :: "+ commentText);
		
		
		processComment.addCommentRepo(commentText, ticketNoTrimmed);
		processComment.closeTicket(ticketNoTrimmed);
		processComment.addCommentSlack(commentText, ticketNoTrimmed);
		
		logger.debug("Ticket closed successfully ");
		
  	  	
  	  	    	
    }
	@Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {}
	
}