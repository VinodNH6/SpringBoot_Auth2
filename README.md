# SpringBoot_Auth2

#If Authorization server is : <My Own running 8080>

#How to create Basic 64 encode using clientId and clientSecret.

1. https://www.base64encode.org/
2. <clientId>:<ClientSecret>    
      i.e vin-study-client:vin-secret-key
3. Click Encode    //It generates encoded string.  //dmluLXN0dWR5LWNsaWVudDp2aW4tc2VjcmV0LWtleQ==
4. Authorization: Basic <Generated-encoded-string>    


//Grant Types allowed
    .authorizedGrantTypes("password", "refresh_token")  //"password" & "refresh_token"
    

#POST to /oauth/token
1. start server at 8080
2. http://localhost:8080/oauth/token

type:

   x-www-form-urlencoded

headers: 

[{"key":"Authorization","value":"Basic dmluLXN0dWR5LWNsaWVudDp2aW4tc2VjcmV0LWtleQ==","description":""},{"key":"Content-Type","value":"application/x-www-form-urlencoded","description":""}]    

Body:

[{"key":"username","value":"myvinemail@gmail.com","description":""},
 {"key":"password","value":"vinodpassword","description":""},
 {"key":"grant_type","value":"password","description":""}]

 
#Output:
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Nzk0ODc2ODgsInVzZXJfbmFtZSI6Im15dmluZW1haWxAZ21haWwuY29tIiwianRpIjoiODdjZDg2YjMtOWIwNi00MzA0LTlhMzYtYWU2NjZmYTgyZjE2IiwiY2xpZW50X2lkIjoidmluLXN0dWR5LWNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.T5rRHXobbN6EdrP-cpmstFm-DeYJIvc0MjQPdhm1Oro",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Nzk0ODc2ODgsInVzZXJfbmFtZSI6Im15dmluZW1haWxAZ21haWwuY29tIiwianRpIjoiM2IzZjY0Y2EtZWU3MC00MmZiLWFiMWMtNDE0MTE4Nzg0ODVkIiwiY2xpZW50X2lkIjoidmluLXN0dWR5LWNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiI4N2NkODZiMy05YjA2LTQzMDQtOWEzNi1hZTY2NmZhODJmMTYifQ.tnxQVosKz84h-wvWAnAF2HHNLAmKXTva7uqWmvPeOWg",
    "expires_in": 19999,
    "scope": "read write",
    "jti": "87cd86b3-9b06-4304-9a36-ae666fa82f16"
}
 
 
#GET : Now access resouce //Authorization with token we got in last step

1. http://localhost:8080/products   //GET

headers: 

[{"key":"Authorization",
  "value":"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Nzk0ODc2ODgsInVzZXJfbmFtZSI6Im15dmluZW1haWxAZ21haWwuY29tIiwianRpIjoiODdjZDg2YjMtOWIwNi00MzA0LTlhMzYtYWU2NjZmYTgyZjE2IiwiY2xpZW50X2lkIjoidmluLXN0dWR5LWNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.T5rRHXobbN6EdrP-cpmstFm-DeYJIvc0MjQPdhm1Oro","description":""}]


 
#If Authorization server is : "Google API"

POST /oauth/token HTTP/1.1
Host: authorization-server.com
 
grant_type=authorization_code
&code=xxxxxxxxxxx
&redirect_uri=https://example-app.com/redirect
&client_id=xxxxxxxxxx
&client_secret=xxxxxxxxxx


#What to do if Access Token expires??
Solution: Get access token again with grant_type:"refresh_token"
Explain:
After an access token expires, using it to make a request from the API will result in an “Invalid Token Error”. At this point, if a refresh token was included when the original access token was issued, it can be used to request a fresh access token from the authorization server.


#links
 https://dotnettutorials.net/lesson/client-validation-using-basic-authentication-web-api/
 
 https://github.com/TechPrimers/spring-security-oauth-mysql-example