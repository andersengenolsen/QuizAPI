// Controller for calls against the API
define(["model/question"], function(Question) {
    
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

    // Public methods
    return {
        // Returning question list
        fetchData: function ( ctrl) {
            return fetchFromApi("http://localhost:8081/api/questions", ctrl);
        }
    };
});