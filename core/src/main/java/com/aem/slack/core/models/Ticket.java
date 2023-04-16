package com.aem.slack.core.models;

import java.util.List;

public class Ticket {
	public Ticket() {
		super();
		
	}
	
	String ticketNum;
	String ticketDesc;
	String ticketStatus;
	String ticketAssignee;
	String raisedBy;
List<Comment> commentList;
	
	public Ticket(List<Comment> commentList) {
		super();
		this.commentList = commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public String getTicketNum() {
		return ticketNum;
	}
	public void setTicketNum(String ticketNum) {
		this.ticketNum = ticketNum;
	}
	public String getTicketDesc() {
		return ticketDesc;
	}
	public void setTicketDesc(String ticketDesc) {
		this.ticketDesc = ticketDesc;
	}
	public String getTicketStatus() {
		return ticketStatus;
	}
	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	public String getTicketAssignee() {
		return ticketAssignee;
	}
	public void setTicketAssignee(String ticketAssignee) {
		this.ticketAssignee = ticketAssignee;
	}
	public String getRaisedBy() {
		return raisedBy;
	}
	public void setRaisedBy(String raisedBy) {
		this.raisedBy = raisedBy;
	}

	

}
