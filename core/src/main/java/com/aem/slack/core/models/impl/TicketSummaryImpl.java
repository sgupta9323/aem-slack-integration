package com.aem.slack.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.aem.slack.core.models.Ticket;
import com.aem.slack.core.models.TicketSummary;
import com.aem.slack.core.service.TicketListService;
import com.aem.slack.core.service.TicketSummaryService;


@Model(adaptables = SlingHttpServletRequest.class,
adapters = TicketSummary.class)
public class TicketSummaryImpl implements TicketSummary {

	@SlingObject
	SlingHttpServletRequest request;
	@OSGiService
	TicketSummaryService ticketsummaryService;
	@Override
	public Ticket getTicketSummary() {
		// TODO Auto-generated method stub
		String ticketId = request.getRequestParameter("ticketId").getString();
		Ticket ticketactivity = ticketsummaryService.getSelectedTicketSummary(ticketId);
		return ticketactivity;
	}

}
