package com.aem.slack.core.service;

import com.aem.slack.core.models.Ticket;

public interface TicketSummaryService {
public Ticket getSelectedTicketSummary(String TicketId);
}
