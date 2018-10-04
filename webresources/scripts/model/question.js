// Module representing a question
define(function() {
   
    function Question(questionTxt, alternatives) {
        this.questionTxt = questionTxt;
        this.alternatives = alternatives;
    }
    
    return Question; 
});