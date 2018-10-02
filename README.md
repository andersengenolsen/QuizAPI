# QuizAPI
API for a Quiz Application.

Developed with Spring MVC and Spring Security.

API will have admin functionality (adding, deleting, editing questions), for user with role admin.

Other users will simply be able to access questions and alternatives.

Admin endpoints :

GET 	/admin/questions - returning all questions

GET 	/admin/questions/{questionId} - returning question with id

POST 	/admin/questions - adding new question

PUT 	/admin/questions - updating / adding question

DELETE	/admin/questions/{questionId} - deleting question with id



