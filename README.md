# aem-slack-integration
This is a project where Adobe Experience Manager(AEM) and Slack messaging channel have been integrated bidirectionally for capturing slack message and coverting them into tickets on AEM platform.

Features:
 - Collates all slack message query from slack channel and pushed them into AEM.
 - AEM populates the slack message queries saved as nodes in JCR to UI.
 - Tracks all the queries on slack without queries getting lost.
 - Support Team can work and reply on queries from AEM user friendly UI whcih allows better managing of ticket.
 

Pre-requisite installations:
 - AEM
 - create one system user named "acluser" with all rights to root node.
 - Slack account on https://slack.com/intl/en-in/get-started#/createnew
 -  configuring slash command and pointing it to your AEM domain (ngrok URL)
 - Ngrok setup https://ngrok.com/docs/getting-started/
 
 Setup 
  - Create a slack account on https://slack.com/intl/en-in/get-started#/createnew
  - Create a Slack Channel .
  - Go to https://api.slack.com/ 
  - Click on "create an app"
  - After creating an app, go to left hand side column and open slash command.
  - Click on "create new command" , make sure to enter your ngrok URL if using localhost AEM setup in request URL.
  - Click on Save.
  - Now, in the same app, open "incoming webhook" and take a note of the webhook URL mentioned.
  
  Flow:
  1. Flow of messages from Slack to AEM.
    - Slash Commands in Slack enablesyou to register and advertise specific commands for your app. With this project, you would need to create one servlet "SlackToAEM" which slack will call when any user will use the slack command.
    - Data from this servlet request call from Slack will be saved in AEM node structure.
    - AEM will then render these saved nodes (queries from slack) to AEM UI.
  2. Flow from AEM to Slack
    - Comments added to AEM UI will also be sent back to Slack for users to see responses of their query.
    - Incoming webhook URL of Slack lets external applications (AEM) to share content to Slack. 
    - We have used incoming webhook URL in servlet "AEMToSlack".
  
 
 
 
 

