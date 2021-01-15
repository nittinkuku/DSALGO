#JWT

JWT (Json Web Token) is used for authorization. After the user is authenticated by server, it creates a JWT token for the client and give it. This JWT is then sent by client with every subsequent request to the server.

**Headers:**
	Authorization: Bearer JWT


**JWT Structure:**

It has 3 parts separated by **.** (DOT)

    Header.Payload.Signature
    
    Header    		//encoded in base 64 
    Payload 		//encoded in base 64
    Signature		//signature given by server based on the values of header, payload and a secret key which only server knows	

Even if a malicious client changes the values in payload or header, the server would be able to tell as the signature for these new values will not match with what is already there in the JWT.
JWT does not contain any sensitive information like password etc. but just enough to identify the user.

**Notes:** 
1. Encoded base 64 - base 64 allows you to convert normal string into a stream of characters.
2. There should not be any sensitive information in the payload as it can be easily decoded and read.

#OAuth
OAuth is meant for authorization and was originally created for authorization between services.

Authorization token contains the permission given to a service.

**Resource:** A Resource is something which needs to be accessed by a requesting service (Client). 

**Resource Owner:** A entity who can grant access to a protected Resource.

**Resource Server:** The Server hosting the protected Resource.

**Client:** The Client is the application which needs Access to the protected Resource.

**Authorization Server:** This takes care of Authorization. It issues access tokens to the Client. It could be a separate server or combined with Resource Server.

**Flow 1: Authorization Code Flow**
1. Resource Owner asks Client to get something from Resource Server
2. Client talks to Authorization Server
3. Authorization Server talks to Resource Owner and verify if he wants to give access to the Client
   It tells the Resource Owner who is the client and what kind of access it is asking for
4. Resource Owner gives the permission
5. Authorization Server gives a short-lived Authorization token to the client
6. Client contacts the Authorization Server with the Authorization Token to get Access Token
7. Authorization Server gives the Access Token to the Client.
8. Client uses the Access Token and asks the Resource Server for access to the protected Resource.
9. Resource Server verifies the Access Token itself or with Authorization Server and if its valid gives access to the Resource.

**Flow 2: Implicit Flow**  
This is less Secure than the first one. It is primarily used with short-lived access tokens.
1. Resource Owner asks Client to get something from Resource Server
2. Client talks to Authorization Server
3. Authorization Server talks to Resource Owner and verify if he wants to give access to the Client
   It tells the Resource Owner who is the client and what kind of access it is asking for
4. Resource Owner gives the permission
5. Authorization Server gives the Access token to the client
6. Client uses the Access Token and asks the Resource Server for access to the protected Resource.
7. Resource Server verifies the Access Token itself or with Authorization Server and if its valid gives access to the Resource.

****Flow 3: Client Credentials Flow**  
This flow can be used when the client is well trusted. Like in the case of microservices where the client is also written by us.
> Lets say there are 2 differenct microservices. Microservice 1 needs to call an api in Microservice 2 as it has access to DB.

1. Microservice 1 calls Authorization Server of Microservice 2 with some kind of key or id which helps the Authorization Server in identifying the Microservice 1.
2. Authorization Server gives the Access Token to Microservice 1.
3. Microservice 1 uses this access token to call an api in Microservice 2.
4. Microservice 2 checks the access token and if its valid gives the result.
