// Controller for calls against the API
define(["model/question", "model/category"], function(Question, Category) {

    // Fetching questions from the API.
    var fetchFromApi = async function(url, ctrl) {
        try {
            const result = await fetch(url);
            const data = await result.json();

            const qList = [];

            // Construction list of questions
            data.forEach((curr) => {
                const question = new Question(curr.questionTxt, curr.alternativeList);
                qList.push(question);
            });

            ctrl.setQuizList(qList);    

        } catch(error) {
            console.log(error);
        }
    };

    var fetchCategories = async function(url, ctrl) {
        try {
            const result = await fetch(url);
            const data = await result.json();

            const categoryList = [];

            data.forEach((curr) => {
                const category = new Category(curr.name);
                categoryList.push(category);
            });

            ctrl.setCategoryList(categoryList);
        } catch (error) {
            console.log(error);
        }
    };

    // Public methods
    return {
        // Returning question list
        fetchData: function (category, ctrl) {
            var url = "http://localhost:8081/quiz/api/questions/category/" + category; 
            return fetchFromApi(url, ctrl);
        }, 
        
        fetchCategories: function(ctrl) {
            return fetchCategories("http://localhost:8081/quiz/api/categories", ctrl);
        }
    };
});