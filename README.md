#Traffic Report Service

#Introduction 
This program is a traffic report service. Takes images from TFL's jam cams, runs the image
against IBM watson's visual classifier, and returns a result (conjested/not conjested). The program then 
pushes this result to a webpage using websockets. 

Currently checks every 5 minutes but that can be changed to suit requirements. 

#Setup
Need to create a config.yaml file in the dir in the format:

service-name : "{api-key}"

Need to have a TFL api-key and an IBM Watson api key.