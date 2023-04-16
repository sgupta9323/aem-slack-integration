package com.aem.slack.core.service.impl;

import com.aem.slack.core.service.ProcessComment;
import com.day.cq.commons.jcr.JcrUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.net.ssl.HttpsURLConnection;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = ProcessComment.class,
name = "ProcessComment",
immediate = true)
public class ProcessCommentImpl implements ProcessComment {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Reference
	ResourceResolverFactory resolverFactory;
	
	@Override
	public void addCommentRepo(String commentText, String ticketNo) {
		// TODO Auto-generated method stub
		logger.debug("In addCommentRepo ");
		
		String nodePath = "/content/tickets";
		String identifierOpen = "/open";
		String identifierClosed = "/closed";
		String identifierComment = "/comments";
  	  
		Map<String, Object> param = new HashMap<>();
		//logger.debug("got param : "+resolverFactory.SUBSERVICE+" end");
	  param.put(ResourceResolverFactory.SUBSERVICE, "ACLReader");
	  //logger.debug("added subsirvice to param"+resolverFactory+" end");
	  try{
	  	ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param);
	  	Session session = resourceResolver.adaptTo(Session.class);
	   	
	  	Node commentParentNode = session.getNode(nodePath+identifierOpen+"/"+ticketNo+identifierComment);
	  	logger.debug("commentParentNode name :: "+commentParentNode.getName());
	  	String commentCount = commentParentNode.getProperty("commentCount").getString();
	  	logger.debug("commentCount :: "+commentCount);
	  	//ticketCount=ticketCount++;
	  	int nextCommentNum = Integer.parseInt(commentCount) + 1;
	  	String nextCommentNumStr = nextCommentNum+"";
		  Node commentNode = commentParentNode.addNode(nextCommentNumStr);
		  //Node day = ticketNode.addNode();
		  commentNode.setProperty("commentText", commentText);
		  commentNode.setProperty("commentBy", "shreya93106");
		   
		  commentParentNode.setProperty("commentCount", nextCommentNumStr);
		   
		  // Save the session changes and log out
		  session.save();
		  session.logout();
	  }catch(Exception e){
			logger.debug("expection thrown :: "+e);
		}

	}

	@Override
	public void addCommentSlack(String commentText, String ticketNo) {
		// TODO Auto-generated method stub
		logger.debug("In addCommentSlack ");
		
		String webhookUrl = "https://hooks.slack.com/services/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";
		//https://hooks.slack.com/services/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
  	String oAuthToken = "xoxb-5127971122176-5102588946165-cbVNXQVat0aPIhYt4oHk3KXf";
  	String slackChannel = "ticketingsystem";
 	 	//String message = "Hellow World";
 	 	//logger.debug("Fetching Slack data in POST");
 	 	String apiMethod = "POST";
  	 	
 	 	StringBuilder msgBuilder = new StringBuilder();
 	 	msgBuilder.append("Comment added to Ticket :: "+ticketNo+"\n Comment :: "+commentText);
 	 //apiRequest(webhookUrl,message, "POST" );
  	 	
  	 
 	 try {
 		 	JSONObject msgJSON = new JSONObject();
 		 	msgJSON.put("text", msgBuilder.toString());
 		 	msgJSON.put("channel", slackChannel);
			//logger.debug(" apiUrl : "+apiUrl);
			URL url = new URL(webhookUrl); 
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setRequestMethod(apiMethod);
			connection.setDoOutput(true);
			
			connection.setRequestProperty("Accept", "application/json");
			
			if(apiMethod.equals("POST")) {
				
				connection.setRequestProperty("Content-Type", "application/json");
				byte[] postData = (msgJSON.toString()).getBytes("utf-8");
				//logger.debug("utf-8 : "+postData);
				try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
				 wr.write(postData);
				}
			}
			
			
			//logger.debug("post wr.write");
			InputStream content = connection.getInputStream();
			//logger.debug("post getting connection");
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			//logger.debug("post inputstreamreader");
			String line;
			while ((line = in.readLine()) != null) {
				//logger.debug("line " + line.toString());
				//varNR = new JSONObject(line); 
			}
			//logger.debug(" results of NR query : "+varNR.toString());
			connection.disconnect();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.debug("EXCEPTION : "+e);
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.debug("EXCEPTION : "+e);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.debug("EXCEPTION : "+e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.debug("EXCEPTION : "+e);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}

	}

	@Override
	public void closeTicket(String ticketNoTrimmed) {
		// TODO Auto-generated method stub
		
		logger.debug("In closeTicket ");
		
		String nodePath = "/content/tickets";
		String identifierOpen = "/open";
		String identifierClosed = "/closed";
		String identifierComment = "/comments";
  	 
		Map<String, Object> param = new HashMap<>();
		//logger.debug("got param : "+resolverFactory.SUBSERVICE+" end");
	  param.put(ResourceResolverFactory.SUBSERVICE, "ACLReader");
	  //logger.debug("added subsirvice to param"+resolverFactory+" end");
	  try{
	  	ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param);
	  	Session session = resourceResolver.adaptTo(Session.class);
	   	
	  	Node openTicketNode = session.getNode(nodePath+identifierOpen+"/"+ticketNoTrimmed);
	  	String ticketName = openTicketNode.getName();
	  	logger.debug("openTicketNode name :: "+openTicketNode.getName());
	   	
	  	//openTicketNode.setProperty("status", "Closed");
	   	
	  	Node closedTicketParentNode = session.getNode(nodePath+identifierClosed);
	  	logger.debug("closedTicketParentNode name :: "+closedTicketParentNode.getName());
	   	
	  	Node newClosedTicketNode = JcrUtil.copy(openTicketNode, closedTicketParentNode, ticketName);
	  	logger.debug("closedTicketParentNode path :: "+newClosedTicketNode.getPath());
	   	
	  	newClosedTicketNode.setProperty("status", "Closed");
	  	openTicketNode.remove();
		  // Save the session changes and log out
		  session.save();
		  session.logout();
	  }catch(Exception e){
			logger.debug("expection thrown :: "+e);
		}
		
	}

	@Override
	public void addAssigneName(String assigneeName, String ticketNoTrimmed) {
logger.debug("In assignee ");
		
		String nodePath = "/content/tickets";
		String identifierOpen = "/open";
		String identifierClosed = "/closed";
		String identifierComment = "/comments";
  	 
		Map<String, Object> param = new HashMap<>();
		//logger.debug("got param : "+resolverFactory.SUBSERVICE+" end");
	  param.put(ResourceResolverFactory.SUBSERVICE, "ACLReader");
	  //logger.debug("added subsirvice to param"+resolverFactory+" end");
	  try{
	  	ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param);
	  	Session session = resourceResolver.adaptTo(Session.class);
	   	
	  	Node ticketNode = session.getNode(nodePath+identifierOpen+"/"+ticketNoTrimmed);
	  	String ticketName = ticketNode.getName();
	  	logger.debug("openTicketNode name :: "+ticketNode.getName());
	   	
	  	//openTicketNode.setProperty("status", "Closed");
	   	
	  	//Node closedTicketParentNode = session.getNode(nodePath+identifierClosed);
	  	//logger.debug("closedTicketParentNode name :: "+closedTicketParentNode.getName());
	   	
	  	//Node newClosedTicketNode = JcrUtil.copy(openTicketNode, closedTicketParentNode, ticketName);
	  	//logger.debug("closedTicketParentNode path :: "+newClosedTicketNode.getPath());
	   	
	  	ticketNode.setProperty("assignee", assigneeName);
	  	//openTicketNode.remove();
		  // Save the session changes and log out
		  session.save();
		  session.logout();
	  }catch(Exception e){
			logger.debug("expection thrown :: "+e);
		}
		
	}
	
}