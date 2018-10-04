// Module representing an alternative
define(function() {
   
    function Alternative(alternativeTxt, correct = false) {
        this.alternativeTxt = alternativeTxt;
        this.correct = correct;
    }
    
    return Alternative;
    
});

