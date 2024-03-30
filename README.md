# QuizAppMicroservices
Base Monolithic QuizApp Version -- https://github.com/samraat777/quizapp

I have converted the monolithic version of quizApp into microservices architecture using the principle of service registry, discovery, API Gateway and interaction between microservices, load balancing and used best design practices to write code.

Overall Project Flow

API GATEWAY -> QUIZ SERVICE -> QUESTION SERVICE

(EUREKA DISCOVERY SERVER) - IT WILL REGISTER ALL THE APPLICATIONS TO BE DISCOVERED BY EACH OTHER
![img5.png](img5.png)
API Gateway Running port - 8765

Services Registered for Discovery with Eureka Server (itself running at 8761)
![img_5.png](img_5.png)

Quiz Service will call Question Service internally
![img_6.png](img_6.png)


Run Configurations

![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)
![img_4.png](img_4.png)



