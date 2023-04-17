# aem-slack-integration

This aem-slack-integration project enables integration between Adobe Experience Manager(AEM) and Slack and helps setup a basic Ticket portal for ABC Org. This project will enable bidirectional connection between AEM and Slack. Messages from Slack using a Slack BOT would be saved and displayed as Tickets in AEM and comments would flow seamlessly from Slack to AEM and AEM to Slack.

The project will enable functionalities like:

 - Raise a query from Slack channel
 - Store these queries raised on Slack channel as Tickets in AEM
 - Display list of all open tickets
 - Display the complete details of a selected Ticket
 - Assign the Ticket to a user
 - Post comments on a Ticket
 - Close the Ticket
 
 
Pre-requisite installations:

 - AEM
 - Slack account with a Channel available in your workspace; https://slack.com/intl/en-in/get-started#/createnew
 - Ngrok setup to expose your local AEM over internet; https://ngrok.com/docs/getting-started/
 
 
 Setup
 
  - Go to https://api.slack.com/
  - Create an app by clicking on "create an app" and attach it to your Slack workspace
  - open the Slack app created and create a slash command.
  - Create a new command by clicking on "create new command" , make sure to enter your ngrok URL if using localhost AEM setup in request URL.
  - Click on Save.
  - Now, in the same app, open "incoming webhook" and take a note of the webhook URL mentioned.
  - Ensure the App has the scopes assigned as “commands” and “Incoming-webhook” in OAuth&Permissions
  
  - Setup AEM following steps in https://experienceleague.adobe.com/docs/experience-manager-65/deploying/deploying/deploy.html?lang=en
  
  - Setup NGORK by following steps in https://ngrok.com/docs/getting-started/
  
  Please find below LINK TO DOWNLOAD RELEVANT ARTIFACTS for this project:
  https://www.dropbox.com/scl/fo/jmrb473o2w0hibtqpa86d/h?dl=0&rlkey=fgm7zi1flflwc4ekcr3j7oqe1
You are ready to go!!
