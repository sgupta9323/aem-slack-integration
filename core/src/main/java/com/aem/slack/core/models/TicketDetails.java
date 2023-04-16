package com.aem.slack.core.models;

import java.util.List;

public interface TicketDetails {
	public List<Ticket> getTickets();

	public String getNextPagePath();

}
