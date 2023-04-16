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
	import org.osgi.service.component.annotations.Activate;
	import org.osgi.service.component.annotations.Reference;
	import org.osgi.service.event.Event;
	import org.osgi.service.event.EventConstants;
	import org.osgi.service.event.EventHandler;
	import org.osgi.service.metatype.annotations.Designate;
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;

	import java.net.HttpURLConnection;
	import java.net.InetAddress;
	import java.net.MalformedURLException;
	import java.net.ProtocolException;
	import java.net.URL;

import javax.jcr.Session;
import javax.net.ssl.HttpsURLConnection;

	import javax.servlet.Servlet;

	import java.rmi.ServerException;

	import org.apache.sling.jcr.api.SlingRepository;
	import org.json.JSONException;
	import org.json.JSONObject;

	import java.io.*;

	@Component(service=Servlet.class,
	property={
	        Constants.SERVICE_DESCRIPTION + "= Send update to Slack",
	        "sling.servlet.methods=" + HttpConstants.METHOD_POST,
	        "sling.servlet.paths="+ "/bin/ticketingsystem/sendupdate"
	})
	public class AEMToSlack extends SlingAllMethodsServlet {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final Logger logger = LoggerFactory.getLogger(getClass());
		@Reference
		private ResourceResolverFactory resolverFactory;
		
		private Session session;
		
		@Reference
		private SlingRepository repository;

	    public void bindRepository(SlingRepository repository) {
	           this.repository = repository;
	           }
		public static void main(String args[]) {
			String webhookUrl = "https://hooks.slack.com/services/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";
			//https://hooks.slack.com/services/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
	    	String oAuthToken = "xoxb-5127971122176-5102588946165-cbVNXQVat0aPIhYt4oHk3KXf";
	    	String slackChannel = "ticketingsystem";
	  	  	String message = "Hellow World";
	  	  	//logger.debug("Fetching Slack data in POST");
	  	  	String apiMethod = "POST";
	  	  	
	  	  	StringBuilder msgBuilder = new StringBuilder();
	  	  	msgBuilder.append(message);
	  	  //apiRequest(webhookUrl,message, "POST" );
	  	  	
	  	  
	  	  try {
	  		  	JSONObject msgJSON = new JSONObject();
	  		  	msgJSON.put("text", message);
	  		  	msgJSON.put("channel", slackChannel);
			
				URL url = new URL(webhookUrl);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setRequestMethod(apiMethod);
				connection.setDoOutput(true);
				
				connection.setRequestProperty("Accept", "application/json");
				
				
				if(apiMethod.equals("POST")) {
					
					connection.setRequestProperty("Content-Type", "application/json");
					byte[] postData = (msgJSON.toString()).getBytes("utf-8");
					
					try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
					  wr.write(postData);
					}
				}
				
				
				
				InputStream content = connection.getInputStream();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(content));
				
				String line;
				while ((line = in.readLine()) != null) {
				}
				
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
			}finally {}
				
			}
		@Override
	    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {
	    	
			logger.debug("Fetching Slack data in POST");
	    	

			String webhookUrl = "https://hooks.slack.com/services/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";
			//https://hooks.slack.com/services/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
	    	String oAuthToken = "xoxb-5127971122176-5102588946165-cbVNXQVat0aPIhYt4oHk3KXf";
	    	String slackChannel = "ticketingsystem";
	  	  	String message = "Hellow World";
	  	  	//logger.debug("Fetching Slack data in POST");
	  	  	String apiMethod = "POST";
	  	  	
	  	  	StringBuilder msgBuilder = new StringBuilder();
	  	  	msgBuilder.append(message);
	  	  //apiRequest(webhookUrl,message, "POST" );
	  	  	
	  	  
	  	  try {
	  		  	JSONObject msgJSON = new JSONObject();
	  		  	msgJSON.put("text", message);
	  		  	msgJSON.put("channel", slackChannel);
				
				URL url = new URL(webhookUrl);
				HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
				connection.setRequestMethod(apiMethod);
				connection.setDoOutput(true);
				
				connection.setRequestProperty("Accept", "application/json");
				
				if(apiMethod.equals("POST")) {
					
					connection.setRequestProperty("Content-Type", "application/json");
					byte[] postData = (msgJSON.toString()).getBytes("utf-8");
					
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
					
				}
				
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
	    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServerException, IOException {}


		



	}
	


