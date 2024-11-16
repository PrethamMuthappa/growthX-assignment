# Growthx Assignment

How to run the project:

Go to the github releases page and download the latest jar file for the release

Run the jar file using command:

```
java -jar growthx-0.0.1-SNAPSHOT.jar
```

The app can also be built from source, just git clone and replace the mongodb credentails and run the application


# API Endpoints


To register a user (if role is not specified, it will be considered as a user)

```
curl -X POST http://localhost:8080/register \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "password": "password123",
}'
```


To login

```
curl -X POST http://localhost:8080/login \
-H "Content-Type: application/json" \
-d '{
    "username": "testuser",
    "password": "password123"
}'
```

To register an admin

```
curl -X POST http://localhost:8080/register \
-H "Content-Type: application/json" \
-d '{
    "username": "admin",
    "password": "password123",
    "role": "ADMIN"
}'
```
To login as an admin

```
curl -X POST http://localhost:8080/login \
-H "Content-Type: application/json" \
-d '{
    "username": "admin",
    "password": "password123"
}'
```

After login, you will get a JWT token, you need to use it in the header of your next request

Make sure to use the same token for the same user, otherwise you will get an error

Make sure to use admin token to access admin endpoints and user token to access user endpoints (both are different!)

To upload an assignment

```
curl -X POST http://localhost:8080/upload \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_JWT_TOKEN" \
-d '{
    "task": "Assignment task description",
    "admin": "admin_username"
}'
```

To fetch all admins

```
curl -X GET http://localhost:8080/admins \
-H "Authorization: Bearer YOUR_JWT_TOKEN"
``` 

To fetch all assignments

```
curl -X GET http://localhost:8080/assignments \
-H "Authorization: Bearer YOUR_JWT_TOKEN"
```

To Accept an assignment

```
curl -X POST http://localhost:8080/assignments/{id}/accept \
-H "Authorization: Bearer YOUR_JWT_TOKEN"
``` 

To Decline an assignment

```
curl -X POST http://localhost:8080/assignments/{id}/reject \
-H "Authorization: Bearer YOUR_JWT_TOKEN"
```     
