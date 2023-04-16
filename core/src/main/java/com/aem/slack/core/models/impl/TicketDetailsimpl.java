package com.aem.slack.core.models.impl;

import java.util.List;


import com.aem.slack.core.models.Ticket;
import com.aem.slack.core.models.TicketDetails;
import com.aem.slack.core.service.TicketListService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

@Model(adaptables = SlingHttpServletRequest.class,
adapters = TicketDetails.class)
public class TicketDetailsimpl implements TicketDetails {

	@OSGiService
	TicketListService ticketListService;

	
	@Override
	public List<Ticket> getTickets() {
		// TODO Auto-generated method stub
		List<Ticket> list = ticketListService.getAllTickets();
		return list;
	}
	
	@Override
	public String getNextPagePath() {
		return "/content/ABCorg/TicketDetails";
	}

}
