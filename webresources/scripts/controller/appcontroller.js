// Global control for the app flow
define(["controller/apicontroller", "controller/quizcontroller"],
       function(apiCtrl, quizCtrl) {

    var setEventListeners = function() {
        var DOM = quizCtrl.getDomStrings();

        // Click listener for "start quiz" button
        document.querySelector(DOM.startQuizSel).addEventListener("click", function() {
            apiCtrl.fetchData(quizCtrl);
        });

        // Click listener for all alternatives in the quiz modal
        document.querySelector(DOM.alternativesSel)
            .addEventListener("click", function(event) {

            // Fetching ID
            var itemId = event.target.id.split("-");
            var id = parseInt(itemId[1]);

            // Checking answer
            quizCtrl.answer(id);
        });

        // Resetting quiz if click outside modal
        document.querySelector(DOM.fadeBgSel)
            .addEventListener("click", function() {
            quizCtrl.reset(); 
        });
    };

    function init() {
        setEventListeners();
    };

    return {
        init: function() {
            setEventListeners();
        }
    }
});