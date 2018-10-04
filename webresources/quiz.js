// Class representing a question
class Question {
    // Constructing question
    constructor(questionTxt, ...alternatives) {
        this.questionTxt = questionTxt;
        this.alternatives = alternatives;
    }
}

// Class representing alternative
// Fed into question
class Alternative {
    constructor(alternativeTxt, isCorrect = false) {
        this.alternativeTxt = alternativeTxt;
        this.isCorrect = isCorrect;
    }
}

// Controller for calls against the API
var ApiController  = (function() {

    // Currently only creating dummy objects
    var fetchFromApi = function(url) {
        var q = new Question("What is Java?", new Alternative("Programming language", true),
                             new Alternative("Car"));
        var q2 = new Question("What is Spring?", new Alternative("Framework", true),
                              new Alternative("Car"));

        var arr = [q,q2];
        return arr;
    };

    // Public methods
    return {
        // Returning question list
        getQuestionList: function () {
            return fetchFromApi("url");
        }
    };
})();

// Controller of the actual Quiz
var QuizController = (function() {

    // List of questions in quiz
    var quizList;

    // Current score
    var score = 0;

    // Current question counter
    var current = 0;

    // DOMStrings
    var DOMStrings = {
        startQuizSel: "#launch-quiz",
        fadeBgSel: ".faded",
        quizModalSel: ".quiz-modal",
        questionTxtSel : "#question-txt",
        alternativesSel : ".alternatives"
    };

    // Html for an alternative input button
    var alternativeHtml = '<input type = "button" class = "btn sm" value = "%alternative%" id = "alternative-%id%">';

    // Setting fade display background to either "block" or "hidden"
    var fadeDisplay = function(isFaded) {
        var displayType;

        if(isFaded)
            displayType = "block";
        else
            displayType = "none";

        document.querySelector(DOMStrings.fadeBgSel).style.display = displayType;
    };

    // Setting quiz modal to either block or hidden
    var modalDisplay = function(isFaded) {
        var displayType;

        if(isFaded)
            displayType = "block";
        else
            displayType = "none";

        document.querySelector(DOMStrings.quizModalSel).style.display = displayType;
    }
    
    // Checking if chosen alternative is correct
    var checkInput = function(alternative) {
        return alternative.isCorrect ? 1 : 0; 
    };
    
    // Running quiz, showing question in qList with index num
    var runQuiz = function(qList, num) {
        // Clearing content if not first question
        if(num > 0) {
            clearContent();
        }
        // Setting new question
        showQuestion(qList[num]);
    };
    
    // Adding question to the UI
    var showQuestion = function(question) {
        // Setting question header
        document.querySelector(DOMStrings.questionTxtSel)
            .innerHTML = question.questionTxt;
        
        // Container for alternatives
        var htmlContainer = DOMStrings.alternativesSel;
        
        // Adding alternatives to the UI
        question.alternatives.forEach((curr, num) => {
            var altHtml = alternativeHtml
            .replace("%id%", num)
            .replace("%alternative%", curr.alternativeTxt);
            document.querySelector(htmlContainer)
                .insertAdjacentHTML("beforeend",altHtml);
        });
    };
    
    // Checking whether an answer is correct or not
    var checkAnswer = function(id) {
        // Looking up alternative
        alt = quizList[current].alternatives[id];
        
        if(alt.isCorrect)
            score++;
        
        current++;
        
        // Continue quiz, or show summary?
        if (current < quizList.length)
            runQuiz(quizList, current);
        else
            showSummary();
    };
    
    // Clearing content in quiz modal
    var clearContent = function() {
        var container = document.querySelector(DOMStrings.alternativesSel);
        var inputs = container.getElementsByTagName("input");

        while(inputs[0])
            inputs[0].remove();
    }
    
    // Show summary, currently writing to console..
    var showSummary = function() {
        console.log("You scored " + score);
        reset();
    }
    
    // Resetting, called when quiz is finished
    var reset = function() {
        clearContent();
        score = 0;
        current = 0;
        fadeDisplay(false);
        modalDisplay(false);        
    }

    return {
        
        // Setting quiz list and starting quiz
        setQuizList: function(qList) {
            quizList = qList;
            fadeDisplay(true);
            modalDisplay(true);
            runQuiz(qList, 0);
        },
        
        // Returning score
        getScore: function() {
            return score; 
        }, 
        
        // Returning DOMStrings
        getDomStrings: function() {
            return DOMStrings;
        },
        
        // Loading id of chosen answer
        answer: function(id) {
            checkAnswer(id);
        }
    };
})();


// Global control for the app flow
var AppController = (function(apiCtrl, quizCtrl) {

    var setEventListeners = function() {
        var DOM = quizCtrl.getDomStrings();
        
        // Click listener for "start quiz" button
        document.querySelector(DOM.startQuizSel).addEventListener("click", function() {
            var qList = apiCtrl.getQuestionList();
            quizCtrl.setQuizList(qList);    
        });
        
        // Click listener for all alternatives in the quiz modal
        document.querySelector(DOM.alternativesSel)
            .addEventListener("click", function(event) {
            
            // Fetching ID
            var itemId = event.target.id.split("-");
            var id = parseInt(itemId[1]);
            
            // Checking answer
            quizCtrl.answer(id);
        })

    };

    return {
        init: function() {
            setEventListeners();
        }
    }
})(ApiController, QuizController);

AppController.init();