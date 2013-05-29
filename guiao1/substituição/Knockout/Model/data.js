function Data(){

	this.letterA = ko.observableArray([
        {old: 'A', subb: ko.observable('A'), freq: ko.observable(0)},
        {old: 'B', subb: ko.observable('B'), freq: ko.observable(0)},
        {old: 'C', subb: ko.observable('C'), freq: ko.observable(0)},
        {old: 'D', subb: ko.observable('D'), freq: ko.observable(0)},
        {old: 'E', subb: ko.observable('E'), freq: ko.observable(0)},
        {old: 'F', subb: ko.observable('F'), freq: ko.observable(0)},
        {old: 'G', subb: ko.observable('G'), freq: ko.observable(0)},
        {old: 'H', subb: ko.observable('H'), freq: ko.observable(0)},
        {old: 'I', subb: ko.observable('I'), freq: ko.observable(0)},
        {old: 'J', subb: ko.observable('J'), freq: ko.observable(0)},
        {old: 'K', subb: ko.observable('K'), freq: ko.observable(0)},
        {old: 'L', subb: ko.observable('L'), freq: ko.observable(0)},
        {old: 'M', subb: ko.observable('M'), freq: ko.observable(0)},
        {old: 'N', subb: ko.observable('N'), freq: ko.observable(0)},
        {old: 'O', subb: ko.observable('O'), freq: ko.observable(0)},
        {old: 'P', subb: ko.observable('P'), freq: ko.observable(0)},
        {old: 'Q', subb: ko.observable('Q'), freq: ko.observable(0)},
        {old: 'R', subb: ko.observable('R'), freq: ko.observable(0)},
        {old: 'S', subb: ko.observable('S'), freq: ko.observable(0)},
        {old: 'T', subb: ko.observable('T'), freq: ko.observable(0)},
        {old: 'U', subb: ko.observable('U'), freq: ko.observable(0)},
        {old: 'V', subb: ko.observable('V'), freq: ko.observable(0)},
        {old: 'W', subb: ko.observable('W'), freq: ko.observable(0)},
        {old: 'X', subb: ko.observable('X'), freq: ko.observable(0)},
        {old: 'Y', subb: ko.observable('Y'), freq: ko.observable(0)},
        {old: 'Z', subb: ko.observable('Z'), freq: ko.observable(0)}
    ]);

    this.freqArray = ko.observableArray([]);

    this.arrayExample = [
        {old: 'A', freq: 0},
        {old: 'B', freq: 0},
        {old: 'C', freq: 0},
        {old: 'D', freq: 0},
        {old: 'E', freq: 0},
        {old: 'F', freq: 0},
        {old: 'G', freq: 0},
        {old: 'H', freq: 0},
        {old: 'I', freq: 0},
        {old: 'J', freq: 0},
        {old: 'K', freq: 0},
        {old: 'L', freq: 0},
        {old: 'M', freq: 0},
        {old: 'N', freq: 0},
        {old: 'O', freq: 0},
        {old: 'P', freq: 0},
        {old: 'Q', freq: 0},
        {old: 'R', freq: 0},
        {old: 'S', freq: 0},
        {old: 'T', freq: 0},
        {old: 'U', freq: 0},
        {old: 'V', freq: 0},
        {old: 'W', freq: 0},
        {old: 'X', freq: 0},
        {old: 'Y', freq: 0},
        {old: 'Z', freq: 0}
    ];

    this.resetExampleArray = function(){
        for(var i = 0; i < this.arrayExample.length; i++)
            this.arrayExample[i].freq = 0;
    }

    this.analyzeFreq = function(){
        this.resetExampleArray();
        var textCipher = document.getElementById('cipherT').value.toUpperCase();

        for(var i = 0; i < textCipher.length; i++){
            var id = textCipher[i].charCodeAt(0) - 'A'.charCodeAt(0);

            var nValue = this.arrayExample[id].freq + 1;
            this.arrayExample[id].freq = nValue;
        }

        this.freqArray.removeAll();

        for(var i = 0; i < this.arrayExample.length; i++)
            this.freqArray.push(this.arrayExample[i]);

        this.freqArray.sort(sortArray);
    }

    function sortArray(left, right) {
        return right.freq - left.freq;
    }

    this.replace = function(){
        var textCipher = document.getElementById('cipherT');
        var cipher1 = textCipher.value.toUpperCase();

        for(var i = 0; i < this.letterA().length; i++){
            cipher1 = cipher1.replaceAll(this.letterA()[i].old.toUpperCase(), this.letterA()[i].subb().toLowerCase());
        }
        
        document.getElementById('clearT').value = cipher1.toUpperCase();        
    }

}

ko.applyBindings(new Data());