### ZooManagerApp

Spring Boot application that allows you to manage the duties of zoo employees. It enables to make simple CRUD request.
The application has three levels of data: Divisions -> Species -> Schedules.
For more detailed documentation, visit http://localhost:8081/swagger-ui.html#/ after starting the application.

### Docker
The application can be also run with docker (Lunux containter): 
docker run -p 8081:8081 damiangor/zoomanagerapp

Log in to one of the profiles:
#profile1
username: user
password: pass
#profile2
username: admin
password: pass