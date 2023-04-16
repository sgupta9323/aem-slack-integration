package com.aem.slack.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;


import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.slack.core.models.Ticket;
import com.aem.slack.core.service.TicketListService;
@Component(name = "TicketListService",
service = TicketListService.class,
immediate = true)
public class TicketListServiceimpl implements TicketListService {
	@Reference
	private ResourceResolverFactory resolverFactory;
	

	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		List<Ticket> list = new ArrayList<Ticket>();
		String nodePath = "/content/tickets";
		String identifierOpen = "/open";
		String identifierClosed = "/closed";
		Map<String, Object> param = new HashMap<>();
	    param.put(ResourceResolverFactory.SUBSERVICE, "ACLReader");
	    

    	ResourceResolver resourceResolver;
		try {
			resourceResolver = resolverFactory.getServiceResourceResolver(param);
		
    	Session session = resourceResolver.adaptTo(Session.class);
	   
    	Node ticketNodeParent = session.getNode(nodePath+identifierOpen);
    	
    	NodeIterator nodeItr = ticketNodeParent.getNodes();
    	while(nodeItr.hasNext()) {
    		
    		Node ticket = nodeItr.nextNode();
    		String ticketNum = ticket.getName();
    		String ticketAssignee = ticket.getProperty("assignee").getString();
    		String raisedBy = ticket.getProperty("raisedBy").getString();
    		String ticketStatus = ticket.getProperty("status").getString();
    		String ticketDesc = ticket.getProperty("query").getString();
    		Ticket ticketPojo = new Ticket();
    		ticketPojo.setRaisedBy(raisedBy);
    		ticketPojo.setTicketAssignee(ticketAssignee);
    		ticketPojo.setTicketDesc(ticketDesc);
    		ticketPojo.setTicketNum(ticketNum);
    		ticketPojo.setTicketStatus(ticketStatus);
    	list.add(ticketPojo);
    	
    	
    	
    	}
    	session.save();
    	session.logout();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}


}
