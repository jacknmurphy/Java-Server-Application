Java-Server-Application
=======================
This is a Java server application built for my Final Year Project. This application works in conjunction with an iOS application which is also posted on this GitHub account.

The aim of the project was to allow users to view Tweets posted within their surrounding location on a map.

This application host a REST service which receives request from the iOS application containing the GPS coordinates of the mobile device. This occurs in the Interface class. When a request is receicved the API class is called and the coordinates passed to it. The API class forms a query using the coordinates as a filter. The API class then queries the Twitter REST API for relevant Tweet data, which is passed back in JSON format. The data is then passed back to the Interface class and HTTP response is sent to the iOS application.
