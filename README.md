# QuizAPI
API for a Quiz Application.

Live version: https://webapps.andersengenolsen.com/quiz/

Demo application developed with Spring MVC and Spring Security.

API will has admin functionality (adding, deleting, editing questions), for user with role admin.

Other users will simply be able to access questions and alternatives.

Endpoints :

GET 	/questions - returning all questions - access: user, admin

GET 	/questions/{questionId} - returning question with id - access: user, admin

POST 	/questions - adding new question - access: admin

PUT 	/questions - updating / adding question - access: admin

DELETE	/questions/{questionId} - deleting question with id - access: admin



