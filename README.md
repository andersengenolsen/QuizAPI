# QuizAPI
API for a Quiz Application.

Developed with Spring MVC and Spring Security.

API will have admin functionality (adding, deleting, editing questions), for user with role admin.

Other users will simply be able to access questions and alternatives.

Admin endpoints :

GET 	/questions - returning all questions - access: user, admin

GET 	/questions/{questionId} - returning question with id - access: user, admin

POST 	/questions - adding new question - access: admin

PUT 	/questions - updating / adding question - access: admin

DELETE	/questions/{questionId} - deleting question with id - access: admin



