# ticket
Test project for MMB

REST API for 
- getting order ticket: /ticket
- getting active ticket: /ticket/active
- removing latest ticket: DELETE /ticket
- and moving active ticket: /ticket/active/next

example of calls:

GET http://localhost:8080/ticket
{"id":1,"created":"2019-03-08T17:34:38.281+0000","number":1,"active":true}

GET http://localhost:8080/ticket
{"id":2,"created":"2019-03-08T17:35:09.375+0000","number":2,"active":false}

GET http://localhost:8080/ticket
{"id":3,"created":"2019-03-08T17:35:38.778+0000","number":3,"active":false}

DELETE http://localhost:8080/ticket
{"id": 3,"created": "2019-03-08T17:35:38.778+0000","number": 3,"active": false}

GET http://localhost:8080/ticket/list
[{"id":1,"created":"2019-03-08T17:34:38.281+0000","number":1,"active":true},{"id":2,"created":"2019-03-08T17:35:09.375+0000","number":2,"active":false}]

GET http://localhost:8080/ticket/active
{"id":1,"created":"2019-03-08T17:34:38.281+0000","number":1,"active":true}

GET http://localhost:8080/ticket/active/next
{"id":2,"created":"2019-03-08T17:35:09.375+0000","number":2,"active":true}

GET http://localhost:8080/ticket/list
[{"id":1,"created":"2019-03-08T17:34:38.281+0000","number":1,"active":false},{"id":2,"created":"2019-03-08T17:35:09.375+0000","number":2,"active":true}]


if moved to different than in memory DB - it will store active state and latest number.


