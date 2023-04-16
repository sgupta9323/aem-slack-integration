package com.aem.slack.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;

import org.apache.log4j.Logger;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.LoggerFactory;

import com.aem.slack.core.models.Comment;
import com.aem.slack.core.models.Ticket;
import com.aem.slack.core.service.TicketListService;
import com.aem.slack.core.service.TicketSummaryService;
@Component(name = "TicketSummaryService",
service = TicketSummaryService.class,
immediate = true)
public class TicketSummaryServiceImpl implements TicketSummaryService {
	@Reference
	  ResourceResolverFactory resolverFactory;
	@Override
	public Ticket getSelectedTicketSummary(String ticketId) {
		// TODO Auto-generated method stub
		
			String nodePath = "/content/tickets";
			String identifierOpen = "/open";
			String identifierClosed = "/closed";
			List <Comment> commentList = new ArrayList<Comment>();
			Ticket ticketPojo = new Ticket();
	  	  
			Map<String, Object> param = new HashMap<>();
			
		  param.put(ResourceResolverFactory.SUBSERVICE, "ACLReader");
		  
		  try{
		  	ResourceResolver resourceResolver = resolverFactory.getServiceResourceResolver(param);
		  	
		  	Session session = resourceResolver.adaptTo(Session.class);
		  	
		  	Node ticketNode = session.getNode(nodePath+identifierOpen+"/"+ticketId);
		  	String ticketDesc = ticketNode.getProperty("query").getString();
	  		String raisedBy = ticketNode.getProperty("raisedBy").getString();
	  		String ticketStatus = ticketNode.getProperty("status").getString();
	  		String ticketAssignee = ticketNode.getProperty("assignee").getString();
		  	
		  	Node commentNode = ticketNode.getNode("comments");
		  	if(commentNode.hasNodes()) {
		  		NodeIterator commentItr = commentNode.getNodes();
		  		while (commentItr.hasNext()){
		  			Comment commentPojo = new Comment();
			   		
			  		Node comment = commentItr.nextNode();
			  		
			  		String commentNum = comment.getName();
			  		String commentText = comment.getProperty("commentText").getString();
			  		String commentBy = comment.getProperty("commentBy").getString();
			  		commentPojo.setCommentNum(commentNum);
			  		commentPojo.setCommentText(commentText);
			  		commentPojo.setCommentBy(commentBy);
			   		
			  		
			   		
			   		
			  		commentList.add(commentPojo);
			  	}
		  	}
		  	ticketPojo.setTicketNum(ticketId);
		  	ticketPojo.setRaisedBy(raisedBy);
	  		ticketPojo.setTicketAssignee(ticketAssignee);
	  		ticketPojo.setTicketDesc(ticketDesc);
	  		ticketPojo.setTicketStatus(ticketStatus);
	  		ticketPojo.setCommentList(commentList);
		  	
	  		 session.save();
			  session.logout();
		  }catch(Exception e){
				
			}
			return ticketPojo;
		
		
	}

}
