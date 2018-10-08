// Controller of the actual Quiz
define(function() {

    // List of questions in quiz
    var quizList;

    // List of categories
    var categoryList;

    // Current score
    var score = 0;

    // Current question counter
    var current = 0;

    // DOMStrings
    var DOMStrings = {
        startQuizSel: ".start-quiz",
        fadeBgSel: ".faded",
        quizModalSel: ".quiz-modal",
        questionTxtSel : "#question-txt",
        alternativesSel : ".alternatives"
    };

    // HTML for categories list
    var categoryHtml = '<a class = "btn" id = "%category%" href = "#">%out%</a>';
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
    };

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
    
    // Populating list with categories
    var populateCategories = function(cList) {
        var htmlContainer = DOMStrings.startQuizSel;

        cList.forEach((curr) => {
            var catHtml = categoryHtml
            .replace("%category%", curr.name)
            .replace("%out%", curr.name);

            document.querySelector(htmlContainer)
                .insertAdjacentHTML("beforeend", catHtml);
        });
    };

    // Checking whether an answer is correct or not
    var checkAnswer = function(id) {
        // Looking up alternative
        alt = quizList[current].alternatives[id];

        if(alt.correct)
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
        document.querySelector(DOMStrings.questionTxtSel)
            .innerHTML = `You scored ${score} out of ${current}`;
        clearContent();
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
            if(qList.length == 0)
                return;
            quizList = qList;
            fadeDisplay(true);
            modalDisplay(true);
            runQuiz(qList, 0);
        },

        setCategoryList: function(cList) {
            populateCategories(cList);
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
        },

        reset: function() {
            reset();
        }
    };
});