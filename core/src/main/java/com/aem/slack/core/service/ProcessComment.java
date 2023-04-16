package com.aem.slack.core.service;

public interface ProcessComment {
	public void addCommentRepo(String commentText, String ticketNo);
	public void addCommentSlack(String commentText, String ticketNo);
	void closeTicket(String ticketNoTrimmed);
	public void addAssigneName(String assigneeName, String ticketNoTrimmed);
}
