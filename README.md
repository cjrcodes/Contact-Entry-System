# Contact-Entry-System
Sample Contact Entry System using the Spring Framework and REST APIs (Java)

My developement environment: Eclipse, Windows 10, Boot Dashboard tool for Eclipse

Notes:
- All functionality accessible through localhost:8080/api/contacts (or localhost:8080/api/contacts/{id} when needed) when tested locally
- H2 Database is used
- As the hibernate.ddl-auto is set to create-drop under application.properties, all data will have to be created manually, using a post method call (as in localhost:8080/api/contacts) a couple of times can fill the tables with some data. Changing the data in each request can help differentiate each record, but is not required. 
- There is dummy data available for other modes (update), although some methods have trouble working in those modes, given that this is time limited, create-drop (empty database) is used and fully functional at this time.

Running the app:
- Clone the project to your machine
- Run the main class file, ContactEntrySystemApplication.java as a Spring Application found under src/main/java/com/cjrcodes/ces in your IDE. Make sure port 8080 is open as that is where the app will reside.
- Use Postman or a comparable tool for testing the API. 

Sample JSON input:

{
"name": {
"first": "Harold",
"middle": "Francis",
"last": "Gilkey"
},
"address": {
"street": "8360 High Autumn Row",
"city": "Cannon",
"state": "Delaware",
"zip": "19797"
},
"phone": [
{
"number": "302-611-9148",
"type": "home"
},
{
"number": "302-532-9427",
"type": "mobile"
}
],
"email": "harold.gilkey@yahoo.com"
}

{ "name": { "first": "Christian", "middle": "", "last": "Rodriguez" }, "address": { "street": "46 Clerk Pl", "city": "Onion", "state": "New Jersey", "zip": "07081" }, "phone": [ { "number": "908-809-7321", "type": "mobile" } ], "email": "cjrcodes@gmail.com" }

{ "name": { "first": "Jonny", "middle": "", "last": "" }, "address": { "street": "1997 Greenwood Road", "city": "London", "state": "Ohio", "zip": "41232" }, "phone": [ { "number": "772-844-7613", "type": "work" } ], "email": "radioheadguitarist@protonmail.com" }

TO-DO (If given extra time):
- Unit Tests
- Page functionality through a templating engine like Thymeleaf
- Experimenting with different ddl modes to allow for dummy data to function with new data
